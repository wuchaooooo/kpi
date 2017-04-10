package com.wuchaooooo.kpi.javabean.vo;

import lombok.Data;

/**
 * Created by wuchaooooo on 26/11/2016.
 */
@Data
public class VStudent extends VUser{
    private String mobile;
    private String email;
    private String school;
    private String major;
    private String employmentStatus;
    private String className;
    private String teacher;
    private double salary;
    private boolean isEmploy;
    private String company;

}
