package com.wuchaooooo.kpi.interceptor;

import com.wuchaooooo.kpi.javabean.po.PUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        String path = request.getContextPath() ;
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

        if (roles.contains("ROLE_STUDENT")){
            response.sendRedirect(basePath+"student/index");
            return;
        } else if (roles.contains("ROLE_ADMIN")) {
            response.sendRedirect(basePath + "admin/index");
            return;
        } else if (roles.contains("ROLE_TEACHER")) {
            response.sendRedirect(basePath + "teacher/index");
            return;
        }

        //获得授权后可得到用户信息   可使用SUserService进行数据库操作
        PUser userDetails = (PUser)authentication.getPrincipal();
        //输出登录提示信息  
        System.out.println("管理员 " + userDetails.getUserName() + " 登录");  
    	
        System.out.println("IP :"+getIpAddress(request));
              
        super.onAuthenticationSuccess(request, response, authentication);  
    }  
    
    public String getIpAddress(HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");    
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {    
            ip = request.getHeader("Proxy-Client-IP");    
        }    
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {    
            ip = request.getHeader("WL-Proxy-Client-IP");    
        }    
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {    
            ip = request.getHeader("HTTP_CLIENT_IP");    
        }    
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {    
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");    
        }    
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {    
            ip = request.getRemoteAddr();    
        }    
        return ip;    
    }  
      
}

