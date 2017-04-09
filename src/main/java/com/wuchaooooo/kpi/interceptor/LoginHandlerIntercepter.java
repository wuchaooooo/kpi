package com.wuchaooooo.kpi.interceptor;

import com.wuchaooooo.kpi.javabean.vo.VLoginUser;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by wuchaooooo on 02/12/2016.
 */
public class LoginHandlerIntercepter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        HttpSession session = request.getSession();
        VLoginUser vLoginUser = (VLoginUser) session.getAttribute("loginUser");
        if (vLoginUser != null) {
            //登陆成功的用户
            return true;
        } else {
            //没有登陆，转向登陆界面
            request.getRequestDispatcher("/login").forward(request, response);
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
