package com.wuchaooooo.kpi.javabean.vo;

import lombok.Data;

import java.util.Date;

/**
 * Created by wuchaooooo on 13/04/2017.
 */
@Data
public class VScoreDaily {
    private long id;
    private String userName;
    private String numOfWeek;
    private String week;
    private String score;
    private Date gmtCreate;
    private Date gmtModify;
}
