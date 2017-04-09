package com.wuchaooooo.kpi.service.impl;

import com.wuchaooooo.kpi.dao.TClazzDAO;
import com.wuchaooooo.kpi.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wuchaooooo on 29/03/2017.
 */
@Service
public class ClazzServiceImpl implements ClazzService{
    @Autowired
    private TClazzDAO clazzDAO;

    @Override
    public List<String> listClazzType() {
        return clazzDAO.listCalzzType();
    }
}
