package com.wuchaooooo.kpi.javabean.po;

/**
 * Created by wuchaooooo on 26/11/2016.
 */
public class PStudent extends PUser{
    private String school;
    private String major;
    private String employmentStatus;
    private String className;

    public PStudent() {
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getEmploymentStatus() {
        return employmentStatus;
    }

    public void setEmploymentStatus(String employmentStatus) {
        this.employmentStatus = employmentStatus;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
