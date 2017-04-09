package com.wuchaooooo.kpi.controller.common;

import com.wuchaooooo.kpi.javabean.vo.VLogin;
import com.wuchaooooo.kpi.javabean.vo.VLoginUser;
import com.wuchaooooo.kpi.javabean.vo.VPassword;
import com.wuchaooooo.kpi.service.IndexService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by wuchaooooo on 24/11/2016.
 */
@Controller
public class IndexController {
    @Autowired
    @Qualifier(value = "indexServiceImpl")
    private IndexService indexService;

    //跳转到登陆界面
    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public String login(
            HttpSession session,
            Map<String, Object> model) {
        session.invalidate();
        model.clear();
        return "login";
    }

    //登陆界面验证用户名密码
    @RequestMapping(value = "/loginConfirm", method = RequestMethod.POST)
    public String loginConfirm(
            @ModelAttribute VLogin vLogin,
            Map<String, Object> model,
            HttpSession session) {
        String userId = vLogin.getUserId();
        String password = vLogin.getPassword();
        String role = vLogin.getRole();
        try {
            String realName = indexService.loginConfirm(role, userId, password);
            if (!realName.equals("")) {
                VLoginUser vLoginUser = new VLoginUser();
                BeanUtils.copyProperties(vLogin, vLoginUser);
                vLoginUser.setRealName(realName);
                session.setAttribute("loginUser", vLoginUser);
                return "redirect:" + role.toLowerCase() + "/index";
            } else {
                model.put("error", "您输入的账号密码有误，请重新输入");
                return "login";
            }
        } catch (Exception c) {
            model.put("error", "数据库读取错误，请稍后重试");
            return "login";
        }
    }

    @RequestMapping(value = "/{role}/modifypwd", method = {RequestMethod.GET, RequestMethod.POST})
    public String changePassword(
            @PathVariable("role") String role,
            @ModelAttribute VPassword vPassword,
            Map<String, Object> model,
            HttpServletRequest request,
            HttpSession session) {
        if (request.getMethod().equalsIgnoreCase("GET")) {
            model.put("role", role);
        } else {
            VLoginUser vLoginUser = (VLoginUser)session.getAttribute("loginUser");
            String message = indexService.changePassword(vLoginUser, vPassword);
            model.put("message", message);
            if (message.equals("密码更新成功")) {
                vLoginUser.setPassword(vPassword.getNewPassword());
            }
        }
        return "common/password";
    }

}
