package com.wuchaooooo.kpi.javabean.vo;

/**
 * Created by wuchaooooo on 30/11/2016.
 */
public class VPassword {
    private String oldPassword;
    private String newPassword;
    private String checkPassword;

    public VPassword() {
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getCheckPassword() {
        return checkPassword;
    }

    public void setCheckPassword(String checkPassword) {
        this.checkPassword = checkPassword;
    }
}
