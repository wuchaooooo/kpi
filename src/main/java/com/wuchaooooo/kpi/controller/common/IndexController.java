package com.wuchaooooo.kpi.controller.common;

import com.wuchaooooo.kpi.javabean.po.PUser;
import com.wuchaooooo.kpi.javabean.vo.VPassword;
import com.wuchaooooo.kpi.service.IndexService;
import com.wuchaooooo.kpi.service.impl.UserService;
import com.wuchaooooo.kpi.utils.AuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(
            @RequestParam(value = "error", required = false) String error,
            Map<String, Object> model) {
        if (error != null) {
            model.put("error", "用户名或密码错误!");
        }
        return "login";
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
            PUser pUser = AuthUtils.getAuthUser();
            String message = indexService.changePassword(pUser, vPassword);
            model.put("message", message);
            if (message.equals("密码更新成功")) {
                pUser.setPassword(vPassword.getNewPassword());
            }
        }
        return "common/password";
    }

}
