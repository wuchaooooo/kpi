package com.wuchaooooo.kpi.javabean.po;

import lombok.Data;

import java.util.Date;

/**
 * Created by wuchaooooo on 06/12/2016.
 */
@Data
public class PFeedback {
    private Integer id;
    private Integer studentId;
    private Date createTime;
    private Date modifyTime;
    private String type;
    private String content;
}
