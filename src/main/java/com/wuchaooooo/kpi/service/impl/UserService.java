package com.wuchaooooo.kpi.service.impl;

import com.wuchaooooo.kpi.dao.UserDAO;
import com.wuchaooooo.kpi.javabean.po.PUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserDAO userDAO;
    
    public PUser getUser(String userName){
    	return userDAO.getUserByUserName(userName);
    }

    public PUser getUser(String userName, String password) {
        return userDAO.getUserByUserNameAndPassword(userName, password);
    }
    
}
