package com.wuchaooooo.kpi.controller.personal;

import com.wuchaooooo.kpi.javabean.AjaxRequestResult;
import com.wuchaooooo.kpi.javabean.po.PUser;
import com.wuchaooooo.kpi.javabean.vo.VKnowledge;
import com.wuchaooooo.kpi.javabean.vo.VStudent;
import com.wuchaooooo.kpi.javabean.vo.VTeacher;
import com.wuchaooooo.kpi.service.ClassService;
import com.wuchaooooo.kpi.service.StudentService;
import com.wuchaooooo.kpi.service.TeacherService;
import com.wuchaooooo.kpi.utils.AuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by wuchaooooo on 26/11/2016.
 */

@Controller
@RequestMapping(value = "/teacher")
@PreAuthorize("hasAuthority('ROLE_TEACHER')")
public class TeacherController {
    @Autowired
    @Qualifier(value = "teacherServiceImpl")
    private TeacherService teacherService;

    @Autowired
    @Qualifier(value = "studentServiceImpl")
    private StudentService studentService;

    @Autowired
    @Qualifier(value = "classServiceImpl")
    private ClassService classService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(HttpSession session, Map<String, Object> model) {
        VTeacher vTeacher = getTeacher();
        model.put("teacher", vTeacher);
        return "common/index";
    }

    @RequestMapping(value = "/personalinfo", method = RequestMethod.GET)
    public String personalInfo(Map<String, Object> model) {
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
        return ajaxRequestResult;
    }

    @RequestMapping(value = "/studentsinfo", method = RequestMethod.GET)
    public String getStudentsInfo(
            Map<String, Object> model) {
        List<VStudent> vStudentList = studentService.listStudent();
        model.put("students", vStudentList);
        return "teacher/info-students";
    }

    @RequestMapping(value = "/score/{type}", method = RequestMethod.GET)
    public String getScore(
            @PathVariable(value = "type") String type,
            @RequestParam(value = "userName", required = false) String userName,
            Map<String, Object> model) {
        model.put("type", type);
        model.put("userName", userName);
        if (userName == null) {

        } else {
            //一周有7天的打分
            List<Integer> days = new ArrayList<>();
            for (int i = 0; i < 7; i++) {
                days.add(i);
            }
            //查询指定学生的分数情况
            if (type.equals("daily")) {
                Map<String, Map<String, String>> map = teacherService.mapScoreDaily(userName);
                Set<String> numOfWeekSet = map.keySet();
                model.put("map", map);
                model.put("numOfWeekList", numOfWeekSet);
                model.put("days", days);
            } else if (type.equals("knowledge")) {
                Map<String, List<VKnowledge>> map = teacherService.mapKnowledge(userName);
                Set<String> numOfWeekSet = map.keySet();
                model.put("map", map);
                model.put("numOfWeekList", numOfWeekSet);
            }
        }
        return "teacher/score-teacher";
    }

    public VTeacher getTeacher() {
        PUser user = AuthUtils.getAuthUser();
        String userName = user.getUserName();
        VTeacher vTeacher = teacherService.getTeacher(userName);
        return vTeacher;
    }
}
