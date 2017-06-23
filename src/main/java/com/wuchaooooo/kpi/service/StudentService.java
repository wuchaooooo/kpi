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
    VStudent getStudent(String userName);

    VStudent getStudent(long studentId);

    VStudent getStudent(String userName, String password);

    List<VStudent> listStudent();

    List<VStudent> listStudent(String ClassName);

    void updatePersonalInfo(VStudent vStudent);

    List<VStudent> getClassmates(String className);

    String insertStudentFile(VStudent vStudent, HttpSession session, MultipartFile file, String renameFile, Logger logger);

    List<VStudentFile> listStudentFile(long studentId);

    VStudentFile getStudentFile(long fileId);

    void downloadStudentFile(Integer fileId, HttpServletRequest request, HttpServletResponse response) throws IOException;

    List<VFeedback> listFeedback(long studentId);

    VFeedback getFeedback(Integer feedbackId);

    long insertFeedback(VFeedback vFeedback, VStudent vStudent);

    void updateFeedback(VFeedback vFeedback);

    void removeFeedback(Integer feedbackId);

    void removeStudent(long studentId);


}
