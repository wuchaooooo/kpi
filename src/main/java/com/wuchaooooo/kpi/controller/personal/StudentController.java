package com.wuchaooooo.kpi.controller.personal;

import com.wuchaooooo.kpi.javabean.AjaxRequestResult;
import com.wuchaooooo.kpi.javabean.vo.VFeedback;
import com.wuchaooooo.kpi.javabean.vo.VLoginUser;
import com.wuchaooooo.kpi.javabean.vo.VStudent;
import com.wuchaooooo.kpi.javabean.vo.VStudentFile;
import com.wuchaooooo.kpi.service.StudentService;
import com.wuchaooooo.kpi.utils.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
@SessionAttributes({"student"})
public class StudentController {

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    @Qualifier(value = "studentServiceImpl")
    private StudentService studentService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(
            HttpSession session,
            Map<String, Object> model) {
        VLoginUser vLoginUser = (VLoginUser) session.getAttribute("loginUser");
        String userId = vLoginUser.getUserId();
        String password = vLoginUser.getPassword();
        VStudent vStudent = studentService.getStudentByUserIdAndPassword(userId, password);
        model.put("role", "student");
        model.put("student", vStudent);
        return "common/index";
    }

    @RequestMapping(value = "/personalinfo", method = RequestMethod.GET)
    public String personalInfo(HttpSession session, Map<String, Object> model) {
        model.put("role", "student");
        return "student/personal-info-student";

//        springmvc默认是转发到相应的模板页面，如果要重定向网址，需要写下述代码
//        return "redirect:personalInfo1";
    }

    @RequestMapping(value = "/personalinfo", method = RequestMethod.PUT)
    @ResponseBody
    public AjaxRequestResult updatePersonalInfo(
            @ModelAttribute VStudent vStudent,
            Map<String, Object> model) {
        AjaxRequestResult ajaxRequestResult = new AjaxRequestResult();
        studentService.updatePersonalInfo(vStudent);
        ajaxRequestResult.setSuccess(true);
        VStudent vStudent1 = studentService.getStudentByUserId(vStudent.getUserId());
        model.put("student", vStudent1);
        model.put("role", "student");
        return ajaxRequestResult;
    }

    @RequestMapping(value = "/uploadfile", method = RequestMethod.GET)
    public String uploadFile(
            @ModelAttribute("student") VStudent vStudent,
            Map<String, Object> model) {
        List<VStudentFile> vStudentFiles = studentService.getStudentFilesByStudentId(vStudent.getId());
        model.put("studentFiles", vStudentFiles);
        model.put("role", "student");
        return "student/upload-student";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(
            @ModelAttribute("student") VStudent vStudent,
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "renameFile", required = false) String renameFile,
            Map<String, Object> model,
            HttpSession session) {
        String returnLocation = studentService.insertStudentFile(vStudent, session, file, renameFile, logger);
        model.put("role", "student");
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
            @ModelAttribute("student") VStudent vStudent,
            Map<String, Object> model) {
        List<VStudent> students = studentService.getClassmates(vStudent.getClassName());
        model.put("classmates", students);
        model.put("role", "student");
        return "student/classmate-student";
    }

    @RequestMapping(value = "/graduate", method = RequestMethod.GET)
    public String graduate(
            @ModelAttribute("student") VStudent vStudent,
            Map<String, Object> model) {
        model.put("role", "student");
        return "student/graduate-student";
    }

    @RequestMapping(value = "/feedback", method = RequestMethod.GET)
    public String feedback(
            @ModelAttribute("student") VStudent vStudent,
            Map<String, Object> model) {
        String dateTime = TimeUtil.dateFormat(new Date());
        model.put("date", dateTime);
        List<VFeedback> vFeedbacks = studentService.getFeedbacksByStudentId(vStudent.getId());
        model.put("feedbacks", vFeedbacks);
        model.put("role", "student");
        return "student/feedback-student";
    }

    @RequestMapping(value = "/feedback", method = RequestMethod.POST)
    public String newFeedback(
            @ModelAttribute("student") VStudent vStudent,
            @ModelAttribute VFeedback vFeedback,
            Map<String, Object> model) {
        studentService.insertFeedback(vFeedback, vStudent);
        model.put("role", "student");
        return "redirect:/student/feedback";
    }

    @RequestMapping(value = "/feedback", method = RequestMethod.PUT)
    @ResponseBody
    public AjaxRequestResult updateFeedback(
            @RequestParam("feedbackId") Integer feedbackId,
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
        VFeedback vFeedback = studentService.getFeedbackById(feedbackId);
        model.put("feedback", vFeedback);
        return "/student/feedback-modal-student";
    }


}
