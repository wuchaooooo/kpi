package com.wuchaooooo.kpi.controller.personal;

import com.sun.org.apache.regexp.internal.RE;
import com.wuchaooooo.kpi.javabean.AjaxRequestResult;
import com.wuchaooooo.kpi.javabean.po.PUser;
import com.wuchaooooo.kpi.javabean.vo.VClass;
import com.wuchaooooo.kpi.javabean.vo.VHeadTeacher;
import com.wuchaooooo.kpi.javabean.vo.VStudent;
import com.wuchaooooo.kpi.service.ClassService;
import com.wuchaooooo.kpi.service.HeadTeacherService;
import com.wuchaooooo.kpi.service.StudentService;
import com.wuchaooooo.kpi.utils.AuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by wuchaooooo on 26/11/2016.
 */

@Controller
@RequestMapping(value = "/headteacher")
@PreAuthorize("hasAuthority('ROLE_HEADTEACHER')")
public class HeadTeacherController {
    @Autowired
    @Qualifier("headTeacherServiceImpl")
    private HeadTeacherService headTeacherService;
    @Autowired
    @Qualifier("studentServiceImpl")
    private StudentService studentService;
    @Autowired
    @Qualifier("classServiceImpl")
    private ClassService classService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Map<String, Object> model) {
        model.put("role", "headTeacher");
        return "common/index";
    }

    @RequestMapping(value = "/personalinfo", method = RequestMethod.GET)
    public String personalInfo(Map<String, Object> model) {
        VHeadTeacher vHeadTeacher = getHeadTeacher();
        model.put("role", "headTeacher");
        model.put("headTeacher", vHeadTeacher);
        return "headTeacher/personal-info-headTeacher";

//        springmvc默认是转发到相应的模板页面，如果要重定向网址，需要写下述代码
//        return "redirect:personalInfo1";
    }

    @RequestMapping(value = "/baseinfo", method = RequestMethod.GET)
    public String baseinfo(Map<String, Object> model) {
        String userName = AuthUtils.getAuthUser().getUserName();
        VClass vClass = classService.getClass(userName);
        List<VStudent> vStudentList = studentService.listStudent(vClass.getClassName());
        model.put("students", vStudentList);
        return "headTeacher/info-headTeacher";
    }

    @RequestMapping(value = "/studentmodal", method = RequestMethod.GET)
    public String studentModal(
            Map<String, Object> model,
            @RequestParam("studentid") long studentId) {
        if (studentId != 0) {
            VStudent vStudent = studentService.getStudent(studentId);
            model.put("student", vStudent);
        }
        return "headTeacher/student-modal-headTeacher";
    }

    @RequestMapping(value = "/classavgsalary", method = RequestMethod.GET)
    public String salary() {
        return "headTeacher/salary-chart-headTeacher";
    }

    @RequestMapping(value = "/salaryranking", method = RequestMethod.GET)
    public String rank() {
        return "headTeacher/rank-chart-headTeacher";
    }

    @RequestMapping(value = "/students/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public AjaxRequestResult removeStudent(@PathVariable("id") long studentId) {
        AjaxRequestResult ajaxRequestResult = new AjaxRequestResult();
        studentService.removeStudent(studentId);
        ajaxRequestResult.setSuccess(true);
        return ajaxRequestResult;
    }


    public VHeadTeacher getHeadTeacher() {
        PUser user = AuthUtils.getAuthUser();
        String userName = user.getUserName();
        VHeadTeacher vHeadTeacher = headTeacherService.getHeadTeacher(userName);
        return vHeadTeacher;
    }
}
