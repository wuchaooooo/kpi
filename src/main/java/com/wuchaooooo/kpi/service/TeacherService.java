package com.wuchaooooo.kpi.service;

import com.wuchaooooo.kpi.javabean.vo.VScoreDaily;
import com.wuchaooooo.kpi.javabean.vo.VTeacher;

import java.util.List;
import java.util.Map;

/**
 * Created by wuchaooooo on 30/11/2016.
 */
public interface TeacherService {

    void updatePersonalInfo(VTeacher vTeacher);

    VTeacher getTeacher(String userName);

    Map<String, Map<String, String>> mapScoreDaily(String userName);
}
