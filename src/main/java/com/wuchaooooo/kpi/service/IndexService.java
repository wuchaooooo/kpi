package com.wuchaooooo.kpi.service;

import com.wuchaooooo.kpi.javabean.vo.VLoginUser;
import com.wuchaooooo.kpi.javabean.vo.VPassword;

/**
 * Created by wuchaooooo on 26/11/2016.
 */
public interface IndexService {
    String loginConfirm(String role, String userId, String password);
    String changePassword(VLoginUser vLoginUser, VPassword vPassword);
}
