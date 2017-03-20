package com.wuchaooooo.kpi.javabean.vo;

/**
 * Created by wuchaooooo on 24/11/2016.
 */
public class VLogin {
    //studentId or jobId
    private String userId;
    //login password
    private String password;
    //login role（student、headTeacher、teacher、interviewer...）
    private String role;

    public VLogin() {
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
