package com.wuchaooooo.kpi.service.impl;

import com.wuchaooooo.kpi.dao.THeadTeacherDAO;
import com.wuchaooooo.kpi.javabean.po.PHeadTeacher;
import com.wuchaooooo.kpi.javabean.vo.VHeadTeacher;
import com.wuchaooooo.kpi.service.HeadTeacherService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wuchaooooo on 30/11/2016.
 */
@Service
public class HeadTeacherServiceImpl implements HeadTeacherService{

    @Autowired
    private THeadTeacherDAO headTeacherDAO;

    @Override
    public VHeadTeacher getHeadTeacher(String userName) {
        PHeadTeacher pHeadTeacher = headTeacherDAO.getHeadTeacherByUserName(userName);
        VHeadTeacher vHeadTeacher = new VHeadTeacher();
        BeanUtils.copyProperties(pHeadTeacher, vHeadTeacher);
        return vHeadTeacher;
    }
}
