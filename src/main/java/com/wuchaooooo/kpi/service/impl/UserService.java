package com.wuchaooooo.kpi.service.impl;

import com.wuchaooooo.kpi.dao.UserDAO;
import com.wuchaooooo.kpi.javabean.po.PUser;
import com.wuchaooooo.kpi.javabean.vo.VUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserDAO userDAO;

    public PUser getUser(long userId) {
        return userDAO.getUserByUserId(userId);
    }
    
    public PUser getUser(String userName){
    	return userDAO.getUserByUserName(userName);
    }

    public PUser getUser(String userName, String password) {
        return userDAO.getUserByUserNameAndPassword(userName, password);
    }

    public void removeUser(long userId) {
        userDAO.removeUser(userId);
    }

    public List<VUser> listUser() {
        List<PUser> pUserList = userDAO.listUser();
        List<VUser> vUserList = copyPUsers2VUsers(pUserList);
        return vUserList;
    }

    public int modifyPassword(String password, String userName) {
        return userDAO.modifyPassword(password, userName);
    }

    private List<VUser> copyPUsers2VUsers(List<PUser> pUserList) {
        List<VUser> vUserList = new ArrayList<>();
        for (PUser pUser : pUserList) {
            VUser vUser = new VUser();
            BeanUtils.copyProperties(pUser, vUser);
            vUserList.add(vUser);
        }
        return vUserList;
    }
    
}
