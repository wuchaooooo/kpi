package com.wuchaooooo.kpi.service;

import com.wuchaooooo.kpi.javabean.vo.VInterviewer;

/**
 * Created by wuchaooooo on 30/11/2016.
 */
public interface InterviewerService {
    VInterviewer getInterviewerByUserIdAndPassword(String userId, String password);

}
