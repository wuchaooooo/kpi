package com.wuchaooooo.kpi.javabean.po;

import lombok.Data;

import java.util.Date;

/**
 * Created by wuchaooooo on 06/12/2016.
 */
@Data
public class PFeedback {
    private long id;
    private long studentId;
    private Date createTime;
    private Date modifyTime;
    private String type;
    private String content;
}
