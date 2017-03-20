package com.wuchaooooo.kpi.javabean.vo;

/**
 * Created by wuchaooooo on 05/12/2016.
 */
public class VLoginUser {
    //studentId or jobId
    private String userId;
    //login password
    private String password;
    //real name
    private String realName;
    //login role（student、headTeacher、teacher、interviewer...）
    private String role;

    public VLoginUser() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
