package com.wuchaooooo.kpi.utils;

import com.wuchaooooo.kpi.javabean.po.PUser;
import com.wuchaooooo.kpi.javabean.vo.VTeacher;
import com.wuchaooooo.kpi.javabean.vo.VUser;
import com.wuchaooooo.kpi.service.StudentService;
import com.wuchaooooo.kpi.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by wuchaooooo on 07/04/2017.
 */
@Component
public class AuthUtils {
//    private static ApplicationContext applicationContext;
//
//    public static void setApplicationContext(ApplicationContext context) {
//        applicationContext = context;
//    }
    private static AuthUtils authUtils;

    @Autowired
    @Qualifier("studentServiceImpl")
    private StudentService studentService;
    @Autowired
    @Qualifier("teacherServiceImpl")
    private TeacherService teacherService;

    @PostConstruct
    public void init() {
        authUtils = this;
        authUtils.studentService = this.studentService;
        authUtils.teacherService = this.teacherService;
    }



    public static long getAuthUserId() {
        SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication auth = ctx.getAuthentication();
        if (auth.getPrincipal() instanceof PUser) {
            PUser user = (PUser) auth.getPrincipal();
            return user.getId();
        }
        return 0;
    }

    public static PUser getAuthUser() {
        SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication auth = ctx.getAuthentication();
        if (auth.getPrincipal() instanceof PUser) {
            PUser user = (PUser) auth.getPrincipal();
            return user;
        }
        return null;
    }

    public VUser getAuthUserDetail() {
        String userName = getAuthUser().getUserName();
        String role = getAuthUser().getRole();
        if (role.equals("student")) {
//            studentService = (StudentService) applicationContext.getBean("studentServiceImpl");
            return authUtils.studentService.getStudent(userName);
        } else if (role.equals("teacher")) {
//            teacherService = (TeacherService) applicationContext.getBean("teacherServiceImpl");
//            AuthUtils authUtils = (AuthUtils) applicationContext.getBean("authUtils");
            return authUtils.teacherService.getTeacher(userName);
        } else if (role.equals("headTeacher")) {

        } else if (role.equals("admin")) {

        }
        return null;
    }

    public static String getAuthUserRole() {
        SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication auth = ctx.getAuthentication();
        if (auth.getPrincipal() instanceof PUser) {
            PUser user= (PUser) auth.getPrincipal();
            String role = "ROLE_" + user.getRole().toUpperCase();
            return role;
        }
        return null;
    }
}
