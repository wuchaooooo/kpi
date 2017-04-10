package com.wuchaooooo.kpi.javabean.vo;

import lombok.Data;

import java.util.Date;

/**
 * Created by wuchaooooo on 06/12/2016.
 */
@Data
public class VFeedback {
    private long id;
    private String StudentName;
    private Date createTime;
    private Date modifyTime;
    private String type;
    private String content;

}
