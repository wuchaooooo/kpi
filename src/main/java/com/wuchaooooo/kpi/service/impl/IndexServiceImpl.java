package com.wuchaooooo.kpi.service.impl;

import com.wuchaooooo.kpi.dao.IndexDao;
import com.wuchaooooo.kpi.javabean.po.PUser;
import com.wuchaooooo.kpi.javabean.vo.VLoginUser;
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
    private IndexDao indexDao;

    @Override
    public String loginConfirm(String role, String userId, String password) {
        String tableName = "";
        if (role.equals("student")) {
            tableName = "t_student";
        } else if (role.equals("headTeacher")) {
            tableName = "t_head_teacher";
        } else if (role.equals("teacher")) {
            tableName = "t_teacher";
        } else if (role.equals("interviewer")) {
            tableName = "t_interviewer";
        } else if (role.equals("investigator")) {
            tableName = "t_investigator";
        } else if (role.equals("guider")) {
            tableName = "t_guider";
        } else if (role.equals("admin")) {
            tableName = "t_admin";
        }
        PUser pUser = indexDao.loginConfirm(tableName, userId, password);
        if (pUser == null) {
            return "";
        } else {
            return pUser.getRealName();
        }
    }

    @Override
    public String changePassword(VLoginUser vLoginUser, VPassword vPassword) {
        String password = vLoginUser.getPassword();
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
        indexDao.changePassword("t_" + vLoginUser.getRole(), newPassword, vLoginUser.getUserId());
        return "密码更新成功";
    }
}
