package com.wuchaooooo.kpi.javabean.vo;

import lombok.Data;

import java.util.Date;

/**
 * Created by wuchaooooo on 29/03/2017.
 */
@Data
public class VClass {
    private long id;
    private String className;
    private Date createDate;
    private Date endDate;
    private String clazzType;
    private String headTeacher;
    private String teacher;
    private Date gmtCreate;
    private Date gmtModify;
}
