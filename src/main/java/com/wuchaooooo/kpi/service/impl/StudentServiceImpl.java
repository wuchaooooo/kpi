package com.wuchaooooo.kpi.service.impl;

import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeUtility;
import com.wuchaooooo.kpi.dao.TClazzDAO;
import com.wuchaooooo.kpi.dao.TFeedbackDAO;
import com.wuchaooooo.kpi.dao.TStudentDAO;
import com.wuchaooooo.kpi.dao.TStudentFileDAO;
import com.wuchaooooo.kpi.javabean.po.*;
import com.wuchaooooo.kpi.javabean.vo.VFeedback;
import com.wuchaooooo.kpi.javabean.vo.VStudent;
import com.wuchaooooo.kpi.javabean.vo.VStudentFile;
import com.wuchaooooo.kpi.service.StudentService;
import com.wuchaooooo.kpi.utils.AuthUtils;
import com.wuchaooooo.kpi.utils.TimeUtil;
import eu.bitwalker.useragentutils.UserAgent;
import org.slf4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wuchaooooo on 26/11/2016.
 */
@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private TStudentDAO studentDao;
    @Autowired
    private TStudentFileDAO studentFileDAO;
    @Autowired
    private TFeedbackDAO feedbackDAO;
    @Autowired
    private TClazzDAO clazzDAO;

    @Override
    public VStudent getStudent(String userName) {
        PStudent pStudent = studentDao.getStudentByUserName(userName);
        VStudent vStudent = new VStudent();
        BeanUtils.copyProperties(pStudent, vStudent);
        return vStudent;
    }

    @Override
    public VStudent getStudent(String userName, String password) {
        PUser pUser = AuthUtils.getAuthUser();
        PStudent pStudent = studentDao.getStudentByUserName(pUser.getUserName());
        VStudent vStudent = new VStudent();
        BeanUtils.copyProperties(pStudent, vStudent);
        return vStudent;
    }

    @Override
    public List<VStudent> listStudent() {
        List<PStudent> pStudentList = studentDao.listStudent();
        List<VStudent> vStudentList = new ArrayList<VStudent>();
        for (PStudent pStudent : pStudentList) {
            VStudent vStudent = new VStudent();
            BeanUtils.copyProperties(pStudent, vStudent);
            String className = pStudent.getClassName();
            PClazz pClazz = clazzDAO.getClazzByClazzName(className);
            vStudent.setTeacher(pClazz.getTeacher());
            vStudentList.add(vStudent);
        }
        return vStudentList;
    }

    @Override
    public void updatePersonalInfo(VStudent vStudent) {
        PStudent pStudent = studentDao.getStudentByUserName(vStudent.getUserName());
        BeanUtils.copyProperties(vStudent, pStudent);
        studentDao.updatePersonalInfo(pStudent);
    }

    @Override
    public List<VStudent> getClassmates(String className) {
        List<PStudent> students = studentDao.getClassmates(className);
        List<VStudent> students1 = new ArrayList<VStudent>();
        for (PStudent student : students) {
            VStudent vStudent = new VStudent();
            BeanUtils.copyProperties(student, vStudent);
            students1.add(vStudent);
        }
        return students1;
    }

    @Override
    public String insertStudentFile(VStudent vStudent, HttpSession session, MultipartFile file, String renameFile, Logger logger) {
        if (file.isEmpty()) {
            session.setAttribute("uploadError", "您上传的文件为空，请重新上传");
        } else {
            // 获取文件名
            String fileName = file.getOriginalFilename();
            // 获取文件的后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf(".") + 1);
            logger.info("上传的后缀名为：" + suffixName);

            //根据前端页面的传值更改文件名
            if (!renameFile.equals("")) {
                fileName = renameFile + "." + suffixName;
            }
            logger.info("上传的文件名为：" + fileName);


            // 文件上传路径
            String filePath = "/Users/wuchaooooo/Documents/";

            // 解决中文问题，liunx下中文路径，图片显示问题
            // fileName = UUID.randomUUID() + suffixName;

            File dest = new File(filePath + fileName);

            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }

            try {
                file.transferTo(dest);
                session.removeAttribute("uploadError");
                PStudentFile pStudentFile = new PStudentFile();
                pStudentFile.setStudentId(vStudent.getId());
                pStudentFile.setName(fileName);
                pStudentFile.setType(suffixName);
                pStudentFile.setPath(filePath + fileName);
                pStudentFile.setCreateTime(new Date());
                studentFileDAO.insertStudentFile(pStudentFile);
            } catch (Exception e) {
                session.setAttribute("uploadError", "文件上传失败，请稍后重试");
            }
        }
        return "redirect:uploadfile";
    }

    @Override
    public List<VStudentFile> listStudentFile(long studentId) {
        List<PStudentFile> studentFiles = studentFileDAO.getStudentFilesByStudentId(studentId);
        List<VStudentFile> vStudentFiles = new ArrayList<VStudentFile>();
        for (PStudentFile s : studentFiles) {
            VStudentFile vStudentFile = new VStudentFile();
            BeanUtils.copyProperties(s, vStudentFile);
            String createTime = TimeUtil.dateFormat(s.getCreateTime());
            vStudentFile.setCreateTime(createTime);
            vStudentFiles.add(vStudentFile);
        }
        return vStudentFiles;
    }

    @Override
    public VStudentFile getStudentFile(long fileId) {
        PStudentFile pStudentFile = studentFileDAO.getSthdentFileByFileId(fileId);
        VStudentFile vStudentFile = new VStudentFile();
        BeanUtils.copyProperties(pStudentFile, vStudentFile);
        return vStudentFile;
    }

    @Override
    public void downloadStudentFile(Integer fileId, HttpServletRequest request, HttpServletResponse response) throws IOException{
        VStudentFile vStudentFile = getStudentFile(fileId);
        String fileName = vStudentFile.getName();
        String path = vStudentFile.getPath();


        File file = new File(path);
        //判断文件是否存在
        if (!file.exists()) {
            return;
        }
        //判断文件类型
        String mimeType = URLConnection.guessContentTypeFromName(file.getName());
        if (mimeType == null) {
            mimeType = "application/octet-stream";
        }
        response.setContentType(mimeType);

        //设置文件响应大小
        response.setContentLengthLong(file.length());

        //文件名编码，解决乱码问题
        String encodedFileName = null;
        String userAgentString = request.getHeader("User-Agent");
        String browser = UserAgent.parseUserAgentString(userAgentString).getBrowser().getGroup().getName();
        if (browser.equals("Chrome") || browser.equals("Internet Exploer") || browser.equals("Safari")) {
            encodedFileName = URLEncoder.encode(fileName, "utf-8").replaceAll("\\+", "%20");
        } else {
            encodedFileName = MimeUtility.encodeWord(fileName);
        }

        //设置Content-Disposition响应头，一方面可以指定下载的文件名，另一方面可以引导浏览器弹出文件下载窗口
        response.setHeader("Content-Disposition", "attachment;fileName=\"" + encodedFileName + "\"");

        //文件下载
        InputStream in = new BufferedInputStream(new FileInputStream(file));
        FileCopyUtils.copy(in, response.getOutputStream());
    }

    @Override
    public List<VFeedback> listFeedback(long studentId) {
        List<PFeedback> pFeedbackList = feedbackDAO.getFeedbacksByStudentId(studentId);
        List<VFeedback> vFeedbackList = new ArrayList<VFeedback>();
        for (PFeedback p : pFeedbackList) {
            VFeedback vFeedback = new VFeedback();
            BeanUtils.copyProperties(p, vFeedback);
            String studentName = studentDao.getStudentByStudentId(studentId).getRealName();
            vFeedback.setStudentName(studentName);
            vFeedbackList.add(vFeedback);
        }
        return vFeedbackList;
    }

    @Override
    public VFeedback getFeedback(Integer feedbackId) {
        PFeedback pFeedback = feedbackDAO.getFeedbackById(feedbackId);
        VFeedback vFeedback = new VFeedback();
        BeanUtils.copyProperties(pFeedback, vFeedback);
        return vFeedback;
    }

    @Override
    public long insertFeedback(VFeedback vFeedback, VStudent vStudent) {
        PFeedback pFeedback = new PFeedback();
        BeanUtils.copyProperties(vFeedback, pFeedback);
        Date date = new Date();
        pFeedback.setCreateTime(date);
        pFeedback.setModifyTime(date);
        pFeedback.setStudentId(vStudent.getId());
        long feedbackId = feedbackDAO.insertFeedback(pFeedback);
        return feedbackId;
    }

    @Override
    public void updateFeedback(VFeedback vFeedback) {
        PFeedback pFeedback = new PFeedback();
        BeanUtils.copyProperties(vFeedback, pFeedback);
        feedbackDAO.updateFeedbackByFeedbackId(pFeedback);
    }

    @Override
    public void removeFeedback(Integer feedbackId) {
        feedbackDAO.removeFeedBack(feedbackId);
    }
}
