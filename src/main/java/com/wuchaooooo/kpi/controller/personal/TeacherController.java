package com.wuchaooooo.kpi.controller.personal;

import com.wuchaooooo.kpi.javabean.AjaxRequestResult;
import com.wuchaooooo.kpi.javabean.po.PUser;
import com.wuchaooooo.kpi.javabean.vo.VStudent;
import com.wuchaooooo.kpi.javabean.vo.VTeacher;
import com.wuchaooooo.kpi.service.ClazzService;
import com.wuchaooooo.kpi.service.StudentService;
import com.wuchaooooo.kpi.service.TeacherService;
import com.wuchaooooo.kpi.utils.AuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created by wuchaooooo on 26/11/2016.
 */

@Controller
@RequestMapping(value = "/teacher")
@SessionAttributes({"teacher"})
public class TeacherController {
    @Autowired
    @Qualifier(value = "teacherServiceImpl")
    private TeacherService teacherService;

    @Autowired
    @Qualifier(value = "studentServiceImpl")
    private StudentService studentService;

    @Autowired
    @Qualifier(value = "clazzServiceImpl")
    private ClazzService clazzService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(HttpSession session, Map<String, Object> model) {
        VTeacher vTeacher = getTeacher();
        model.put("role", "teacher");
        model.put("teacher", vTeacher);
        return "common/index";
    }

    @RequestMapping(value = "/personalinfo", method = RequestMethod.GET)
    public String personalInfo(HttpSession session, Map<String, Object> model) {
        model.put("role", "teacher");
        return "teacher/personal-info-teacher";
//        springmvc默认是转发到相应的模板页面，如果要重定向网址，需要写下述代码
//        return "redirect:personalInfo1";
    }

    @RequestMapping(value = "/personalinfo", method = RequestMethod.PUT)
    @ResponseBody
    public AjaxRequestResult updatePersonalInfo(
            @ModelAttribute VTeacher vTeacher,
            Map<String, Object> model) {
        AjaxRequestResult ajaxRequestResult = new AjaxRequestResult();
        teacherService.updatePersonalInfo(vTeacher);
        ajaxRequestResult.setSuccess(true);
        VTeacher vTeacher1 = teacherService.getTeacher(vTeacher.getUserName());
        model.put("teacher", vTeacher1);
        model.put("role", "teacher");
        return ajaxRequestResult;
    }

    @RequestMapping(value = "/studentsinfo", method = RequestMethod.GET)
    public String getStudentsInfo(
            Map<String, Object> model) {
        model.put("role", "teacher");
        List<VStudent> vStudentList = studentService.listStudent();
        model.put("students", vStudentList);
        return "teacher/info-students";
    }

    @RequestMapping(value = "/score/{type}", method = RequestMethod.GET)
    public String getScore(
            @RequestParam(value = "type") String type,
            Map<String, Object> model) {
        return "teacher/score-teacher";
    }

    public VTeacher getTeacher() {
        PUser user = AuthUtils.getAuthUser();
        String userName = user.getUserName();
        String password = user.getPassword();
        VTeacher vTeacher = teacherService.getTeacher(userName, password);
        return vTeacher;
    }
}
