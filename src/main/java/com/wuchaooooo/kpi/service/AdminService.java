package com.wuchaooooo.kpi.service;

import com.wuchaooooo.kpi.javabean.vo.VAdmin;

/**
 * Created by wuchaooooo on 30/11/2016.
 */
public interface AdminService {
    VAdmin getAdminByUserIdAndPassword(String userId, String password);

    VAdmin getAdmin(String userName);

}
