package com.wuchaooooo.kpi.controller.personal;

import com.wuchaooooo.kpi.javabean.po.PUser;
import com.wuchaooooo.kpi.javabean.vo.VHeadTeacher;
import com.wuchaooooo.kpi.javabean.vo.VStudent;
import com.wuchaooooo.kpi.service.HeadTeacherService;
import com.wuchaooooo.kpi.utils.AuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Map<String, Object> model) {
        model.put("role", "headTeacher");
        return "common/index";
    }

    @RequestMapping(value = "/personalinfo", method = RequestMethod.GET)
    public String personalInfo(Map<String, Object> model) {
        VHeadTeacher vHeadTeacher = getHeadTeacher();
        model.put("role", "student");
        model.put("headTeacher", vHeadTeacher);
        return "headTeacher/personal-info-headTeacher";

//        springmvc默认是转发到相应的模板页面，如果要重定向网址，需要写下述代码
//        return "redirect:personalInfo1";
    }

    @RequestMapping(value = "/baseinfo", method = RequestMethod.GET)
    public String baseinfo(Map<String, Object> model) {
        return "headTeacher/info-headTeacher";
    }

    @RequestMapping(value = "/classavgsalary", method = RequestMethod.GET)
    public String salary() {
        return "headTeacher/salary-chart-headTeacher";
    }

    @RequestMapping(value = "salaryranking", method = RequestMethod.GET)
    public String rank() {
        return "headTeacher/rank-chart-headTeacher";
    }


    public VHeadTeacher getHeadTeacher() {
        PUser user = AuthUtils.getAuthUser();
        String userName = user.getUserName();
        VHeadTeacher vHeadTeacher = headTeacherService.getHeadTeacher(userName);
        return vHeadTeacher;
    }
}
