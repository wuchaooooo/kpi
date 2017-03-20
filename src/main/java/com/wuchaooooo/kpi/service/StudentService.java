package com.wuchaooooo.kpi.service;

import com.wuchaooooo.kpi.javabean.vo.VFeedback;
import com.wuchaooooo.kpi.javabean.vo.VStudent;
import com.wuchaooooo.kpi.javabean.vo.VStudentFile;
import org.slf4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by wuchaooooo on 26/11/2016.
 */
public interface StudentService {
    VStudent getStudentByUserId(String userId);

    VStudent getStudentByUserIdAndPassword(String userId, String password);

    void updatePersonalInfo(VStudent vStudent);

    List<VStudent> getClassmates(String className);

    String insertStudentFile(VStudent vStudent, HttpSession session, MultipartFile file, String renameFile, Logger logger);

    List<VStudentFile> getStudentFilesByStudentId(Integer studentId);

    VStudentFile getSthdentFileByFileId(Integer fileId);

    void downloadStudentFile(Integer fileId, HttpServletRequest request, HttpServletResponse response) throws IOException;

    List<VFeedback> getFeedbacksByStudentId(Integer studentId);

    VFeedback getFeedbackById(Integer feedbackId);

    Integer insertFeedback(VFeedback vFeedback, VStudent vStudent);

    void updateFeedback(VFeedback vFeedback);

    void removeFeedback(Integer feedbackId);


}
