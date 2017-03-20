package com.wuchaooooo.kpi.service.impl;

import com.wuchaooooo.kpi.dao.TAdminDAO;
import com.wuchaooooo.kpi.javabean.po.PAdmin;
import com.wuchaooooo.kpi.javabean.vo.VAdmin;
import com.wuchaooooo.kpi.service.AdminService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wuchaooooo on 30/11/2016.
 */
@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    private TAdminDAO adminDAO;

    @Override
    public VAdmin getAdminByUserIdAndPassword(String userId, String password) {
        PAdmin pAdmin = adminDAO.getAdminByUserIdAndPassword(userId, password);
        VAdmin vAdmin = new VAdmin();
        BeanUtils.copyProperties(pAdmin, vAdmin);
        return vAdmin;
    }
}
