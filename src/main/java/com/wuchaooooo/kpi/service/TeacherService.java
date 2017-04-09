package com.wuchaooooo.kpi.service;

import com.wuchaooooo.kpi.javabean.vo.VTeacher;

/**
 * Created by wuchaooooo on 30/11/2016.
 */
public interface TeacherService {
    VTeacher getTeacherByUserIdAndPassword(String userId, String password);

    void updatePersonalInfo(VTeacher vTeacher);

    VTeacher getTeacherByUserId(String userId);
}
