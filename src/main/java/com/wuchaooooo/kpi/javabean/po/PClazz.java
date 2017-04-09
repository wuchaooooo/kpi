package com.wuchaooooo.kpi.javabean.po;

import lombok.Data;

import java.util.Date;

/**
 * Created by wuchaooooo on 29/03/2017.
 */
@Data
public class PClazz {
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
