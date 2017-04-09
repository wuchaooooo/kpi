package com.wuchaooooo.kpi.javabean.po;

import lombok.Data;

import java.util.Date;

/**
 * Created by wuchaooooo on 02/12/2016.
 */
@Data
public class PStudentFile {
    private Integer id;
    private Integer studentId;
    private String name;
    private String type;
    private String path;
    private Date createTime;

}
