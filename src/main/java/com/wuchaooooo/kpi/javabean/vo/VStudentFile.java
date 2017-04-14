package com.wuchaooooo.kpi.javabean.vo;

import lombok.Data;

/**
 * Created by wuchaooooo on 02/12/2016.
 */
@Data
public class VStudentFile {
    private long id;
    private long studentId;
    private String name;
//    private String type;
    private String path;
    private String createTime;

}
