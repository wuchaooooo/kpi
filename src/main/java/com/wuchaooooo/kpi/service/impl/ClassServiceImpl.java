package com.wuchaooooo.kpi.service.impl;

import com.wuchaooooo.kpi.dao.TClazzDAO;
import com.wuchaooooo.kpi.javabean.po.PClazz;
import com.wuchaooooo.kpi.javabean.vo.VClass;
import com.wuchaooooo.kpi.service.ClassService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wuchaooooo on 29/03/2017.
 */
@Service
public class ClassServiceImpl implements ClassService {
    @Autowired
    private TClazzDAO clazzDAO;

    @Override
    public List<String> listClassType() {
        return clazzDAO.listCalzzType();
    }

    @Override
    public VClass getClass(String headTeacher) {
        PClazz pClazz = clazzDAO.getClassByHeadTeacher(headTeacher);
        VClass vClass = new VClass();
        BeanUtils.copyProperties(pClazz, vClass);
        return vClass;
    }
}
