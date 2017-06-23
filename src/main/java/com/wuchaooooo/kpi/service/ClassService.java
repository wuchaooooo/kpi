package com.wuchaooooo.kpi.service;

import com.wuchaooooo.kpi.javabean.vo.VClass;

import java.util.List;

/**
 * Created by wuchaooooo on 29/03/2017.
 */
public interface ClassService {
    List<String> listClassType();

    VClass getClass(String headTeacher);

}
