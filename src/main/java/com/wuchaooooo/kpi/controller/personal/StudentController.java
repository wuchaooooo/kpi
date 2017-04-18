package com.wuchaooooo.kpi.controller.personal;

import com.wuchaooooo.kpi.javabean.AjaxRequestResult;
import com.wuchaooooo.kpi.javabean.po.PUser;
import com.wuchaooooo.kpi.javabean.vo.VFeedback;
import com.wuchaooooo.kpi.javabean.vo.VScoreDaily;
import com.wuchaooooo.kpi.javabean.vo.VStudent;
import com.wuchaooooo.kpi.javabean.vo.VStudentFile;
import com.wuchaooooo.kpi.service.StudentService;
import com.wuchaooooo.kpi.utils.AuthUtils;
import com.wuchaooooo.kpi.utils.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by wuchaooooo on 24/11/2016.
 */
@Controller
@RequestMapping(value = "/student")
@PreAuthorize("hasAnyAuthority('ROLE_STUDENT')")
public class StudentController {

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    @Qualifier(value = "studentServiceImpl")
    private StudentService studentService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(
            Map<String, Object> model) {
        VStudent vStudent = getStudent();
        model.put("role", "student");
        model.put("student", vStudent);
        return "common/index";
    }

    @RequestMapping(value = "/personalinfo", method = RequestMethod.GET)
    public String personalInfo(Map<String, Object> model) {
        VStudent vStudent = getStudent();
        model.put("role", "student");
        model.put("student", vStudent);
        return "student/personal-info-student";

//        springmvc默认是转发到相应的模板页面，如果要重定向网址，需要写下述代码
//        return "redirect:personalInfo1";
    }

    @RequestMapping(value = "/personalinfo", method = RequestMethod.PUT)
    @ResponseBody
    public AjaxRequestResult updatePersonalInfo(
            @ModelAttribute VStudent vStudent,
            Map<String, Object> model) {
        model.put("role", "student");
        model.put("student", vStudent);
        AjaxRequestResult ajaxRequestResult = new AjaxRequestResult();
        studentService.updatePersonalInfo(vStudent);
        ajaxRequestResult.setSuccess(true);
        return ajaxRequestResult;
    }

    @RequestMapping(value = "/uploadfile", method = RequestMethod.GET)
    public String uploadFile(
            Map<String, Object> model) {
        VStudent vStudent = getStudent();
        model.put("role", "student");
        model.put("student", vStudent);
        List<VStudentFile> vStudentFiles = studentService.listStudentFile(vStudent.getId());
        model.put("studentFiles", vStudentFiles);
        model.put("role", "student");
        return "student/upload-student";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "renameFile", required = false) String renameFile,
            Map<String, Object> model,
            HttpSession session) {
        VStudent vStudent = getStudent();
        model.put("role", "student");
        model.put("student", vStudent);
        String returnLocation = studentService.insertStudentFile(vStudent, session, file, renameFile, logger);
        VStudent vStudent1 = getStudent();
        model.put("role", "student");
        model.put("student", vStudent1);
        return returnLocation;
    }

    @RequestMapping(value = "/download/{fileId}", method = RequestMethod.GET)
    public void download(
            @PathVariable("fileId") Integer fileId,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        studentService.downloadStudentFile(fileId, request, response);
    }

    @RequestMapping(value = "/classmates", method = RequestMethod.GET)
    public String classmates(
            Map<String, Object> model) {
        VStudent vStudent = getStudent();
        model.put("role", "student");
        model.put("student", vStudent);
        List<VStudent> students = studentService.getClassmates(vStudent.getClassName());
        model.put("classmates", students);
        return "student/classmate-student";
    }

    @RequestMapping(value = "/graduate", method = RequestMethod.GET)
    public String graduate(
            Map<String, Object> model) {
        VStudent vStudent = getStudent();
        model.put("student", vStudent);
        model.put("role", "student");
        List<VStudent> vStudentList = studentService.listStudent();
        model.put("students", vStudentList);
        return "student/graduate-student";
    }

    @RequestMapping(value = "/feedback", method = RequestMethod.GET)
    public String feedback(
            Map<String, Object> model) {
        VStudent vStudent = getStudent();
        model.put("role", "student");
        model.put("student", vStudent);

        String dateTime = TimeUtil.dateFormat(new Date());
        model.put("date", dateTime);
        List<VFeedback> vFeedbacks = studentService.listFeedback(vStudent.getId());
        model.put("feedbacks", vFeedbacks);
        return "student/feedback-student";
    }

    @RequestMapping(value = "/feedback", method = RequestMethod.POST)
    public String newFeedback(
            @ModelAttribute VFeedback vFeedback) {
        VStudent vStudent = getStudent();
        studentService.insertFeedback(vFeedback, vStudent);
        return "redirect:/student/feedback";
    }

    @RequestMapping(value = "/feedback", method = RequestMethod.PUT)
    @ResponseBody
    public AjaxRequestResult updateFeedback(
            @RequestParam("feedbackId") long feedbackId,
            @ModelAttribute VFeedback vFeedback) {
        AjaxRequestResult ajaxRequestResult = new AjaxRequestResult();
        try {
            vFeedback.setModifyTime(new Date());
            vFeedback.setId(feedbackId);
            studentService.updateFeedback(vFeedback);
            ajaxRequestResult.setSuccess(true);
        } catch (Exception e) {
            ajaxRequestResult.setSuccess(false);
        }
        return ajaxRequestResult;
    }

    @RequestMapping(value = "/feedback", method = RequestMethod.DELETE)
    @ResponseBody
    public AjaxRequestResult updateFeedback(
            @RequestParam("feedbackId") Integer feedbackId) {
        AjaxRequestResult ajaxRequestResult = new AjaxRequestResult();
        try {
            studentService.removeFeedback(feedbackId);
            ajaxRequestResult.setSuccess(true);
        } catch (Exception e) {
            ajaxRequestResult.setSuccess(false);
        }
        return ajaxRequestResult;
    }

    @RequestMapping(value = "/feedbackmodal", method = RequestMethod.GET)
    public String feedbackModal(
            @RequestParam("feedbackId") Integer feedbackId,
            Map<String, Object> model) {
        VFeedback vFeedback = studentService.getFeedback(feedbackId);
        model.put("feedback", vFeedback);
        return "/student/feedback-modal-student";
    }

    public VStudent getStudent() {
        PUser user = AuthUtils.getAuthUser();
        String userName = user.getUserName();
        String password = user.getPassword();
        VStudent vStudent = studentService.getStudent(userName, password);
        return vStudent;
    }


}
