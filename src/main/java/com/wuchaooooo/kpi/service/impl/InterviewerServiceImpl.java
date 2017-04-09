package com.wuchaooooo.kpi.service.impl;

import com.wuchaooooo.kpi.dao.TInterviewerDAO;
import com.wuchaooooo.kpi.javabean.po.PInterviewer;
import com.wuchaooooo.kpi.javabean.vo.VInterviewer;
import com.wuchaooooo.kpi.service.InterviewerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wuchaooooo on 30/11/2016.
 */
@Service
public class InterviewerServiceImpl implements InterviewerService{

    @Autowired
    private TInterviewerDAO interviewerDao;

    @Override
    public VInterviewer getInterviewerByUserIdAndPassword(String userId, String password) {
        PInterviewer pInterviewer = interviewerDao.getInterviewerByUserIdAndPassword(userId, password);
        VInterviewer vInterviewer = new VInterviewer();
        BeanUtils.copyProperties(pInterviewer, vInterviewer);
        return vInterviewer;
    }
}
