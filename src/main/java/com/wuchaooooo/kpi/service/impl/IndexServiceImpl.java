package com.wuchaooooo.kpi.service.impl;

import com.wuchaooooo.kpi.dao.UserDAO;
import com.wuchaooooo.kpi.javabean.po.PUser;
import com.wuchaooooo.kpi.javabean.vo.VPassword;
import com.wuchaooooo.kpi.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wuchaooooo on 26/11/2016.
 */
@Service
public class IndexServiceImpl implements IndexService {
    @Autowired
    private UserDAO userDAO;

    @Override
    public String changePassword(PUser pUser, VPassword vPassword) {
        String password = pUser.getPassword();
        String oldPassword = vPassword.getOldPassword();
        String newPassword = vPassword.getNewPassword();
        String checkPassword = vPassword.getCheckPassword();
        if (!password.equals(oldPassword)) {
            return "您输入的原始密码有误，请重新输入";
        } else if (!newPassword.equals(checkPassword)) {
            return "您两次输入的新密码不一致，请重新输入";
        } else if (oldPassword.equals(newPassword)) {
            return "您两次输入的新密码不一致，请重新输入";
        }
        userDAO.changePassword(newPassword, pUser.getUserName());
        return "密码更新成功";
    }
}
