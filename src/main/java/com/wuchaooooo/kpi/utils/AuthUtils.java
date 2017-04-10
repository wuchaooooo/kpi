package com.wuchaooooo.kpi.utils;

import com.wuchaooooo.kpi.javabean.po.PUser;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by wuchaooooo on 07/04/2017.
 */
public class AuthUtils {
    private static ApplicationContext applicationContext;

    public static void setApplicationContext(ApplicationContext context) {
        applicationContext = context;
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
}
