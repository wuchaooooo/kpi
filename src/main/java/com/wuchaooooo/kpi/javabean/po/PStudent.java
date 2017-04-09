package com.wuchaooooo.kpi.javabean.po;

import lombok.Data;

/**
 * Created by wuchaooooo on 26/11/2016.
 */
@Data
public class PStudent extends PUser{
    private String school;
    private String major;
    private String employmentStatus;
    private String className;
}
