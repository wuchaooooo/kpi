package com.wuchaooooo.kpi.controller.personal;

import com.wuchaooooo.kpi.javabean.AjaxRequestResult;
import com.wuchaooooo.kpi.javabean.po.PUser;
import com.wuchaooooo.kpi.javabean.vo.VAdmin;
import com.wuchaooooo.kpi.javabean.vo.VHeadTeacher;
import com.wuchaooooo.kpi.javabean.vo.VUser;
import com.wuchaooooo.kpi.service.AdminService;
import com.wuchaooooo.kpi.service.impl.UserService;
import com.wuchaooooo.kpi.utils.AuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by wuchaooooo on 26/11/2016.
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    @Qualifier("adminServiceImpl")
    private AdminService adminService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Map<String, Object> model) {
        model.put("role", "admin");
        return "common/index";
    }

    @RequestMapping(value = "/personalinfo", method = RequestMethod.GET)
    public String personalInfo(Map<String, Object> model) {
        VAdmin vAdmin = getAdmin();
        model.put("role", "admin");
        model.put("admin", vAdmin);
        return "admin/personal-info-admin";

//        springmvc默认是转发到相应的模板页面，如果要重定向网址，需要写下述代码
//        return "redirect:personalInfo1";
    }

    @RequestMapping(value = "/accounts", method = RequestMethod.GET)
    public String accounts(Map<String, Object> model) {
        List<VUser> vUserList = userService.listUser();

        model.put("users", vUserList);
        return "admin/accounts-admin";
    }

    @RequestMapping(value = "/accountmodal", method = RequestMethod.GET)
    public String accountModel(
            Map<String, Object> model,
            @RequestParam("userid") long userId) {
        if (userId != 0){
            PUser pUser = userService.getUser(userId);
            model.put("user", pUser);
        }
        return "admin/account-modal-admin";
    }

    @RequestMapping(value = "/accountpwd", method = RequestMethod.GET)
    public String modifyPwd(@RequestParam(value = "userid") long userId) {
        return "admin/account-modifypwd-modal-admin";
    }

    @RequestMapping(value = "/accounts/{userId}", method = RequestMethod.DELETE)
    @ResponseBody
    public AjaxRequestResult removeUser(@PathVariable(value = "userId") long userId) {
        userService.removeUser(userId);
        AjaxRequestResult ajaxRequestResult = new AjaxRequestResult();
        ajaxRequestResult.setSuccess(true);
        return ajaxRequestResult;
    }

    public VAdmin getAdmin() {
        PUser user = AuthUtils.getAuthUser();
        String userName = user.getUserName();
        VAdmin vAdmin = adminService.getAdmin(userName);
        return vAdmin;
    }
}
