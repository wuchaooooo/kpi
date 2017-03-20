package com.wuchaooooo.kpi.service.impl;

import com.wuchaooooo.kpi.dao.TTeacherDAO;
import com.wuchaooooo.kpi.javabean.po.PTeacher;
import com.wuchaooooo.kpi.javabean.vo.VTeacher;
import com.wuchaooooo.kpi.service.TeacherService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wuchaooooo on 30/11/2016.
 */
@Service
public class TeacherServiceImpl implements TeacherService{
    @Autowired
    private TTeacherDAO teacherDao;

    @Override
    public VTeacher getTeacherByUserIdAndPassword(String userId, String password) {
        PTeacher pTeacher = teacherDao.getTeacherByUserIdAndPassword(userId, password);
        VTeacher vTeacher = new VTeacher();
        BeanUtils.copyProperties(pTeacher, vTeacher);
        return vTeacher;
    }
}