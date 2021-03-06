package com.wuchaooooo.kpi.service.impl;

import com.wuchaooooo.kpi.dao.KnowledgeDAO;
import com.wuchaooooo.kpi.dao.ScoreDailyDAO;
import com.wuchaooooo.kpi.dao.TTeacherDAO;
import com.wuchaooooo.kpi.dao.UserDAO;
import com.wuchaooooo.kpi.javabean.po.PKnowledge;
import com.wuchaooooo.kpi.javabean.po.PScoreDaily;
import com.wuchaooooo.kpi.javabean.po.PTeacher;
import com.wuchaooooo.kpi.javabean.vo.VKnowledge;
import com.wuchaooooo.kpi.javabean.vo.VScoreDaily;
import com.wuchaooooo.kpi.javabean.vo.VTeacher;
import com.wuchaooooo.kpi.service.TeacherService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by wuchaooooo on 30/11/2016.
 */
@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private TTeacherDAO teacherDao;
    @Autowired
    private ScoreDailyDAO scoreDailyDAO;
    @Autowired
    private KnowledgeDAO knowledgeDAO;

    @Override
    public void updatePersonalInfo(VTeacher vTeacher) {
        PTeacher pTeacher = new PTeacher();
        BeanUtils.copyProperties(vTeacher, pTeacher);
        teacherDao.updatePersonalInfo(pTeacher);
    }

    @Override
    public VTeacher getTeacher(String userName) {
        PTeacher pTeacher = teacherDao.getTeacherByUserName(userName);
        VTeacher vTeacher = new VTeacher();
        BeanUtils.copyProperties(pTeacher, vTeacher);
        return vTeacher;
    }

    @Override
    public Map<String, Map<String, String>> mapScoreDaily(String userName) {
        Map<String, Map<String, String>> map = new LinkedHashMap<>();
        List<String> numOfWeekList = scoreDailyDAO.listNumOfWeek(userName);
        for (String numOfWeek : numOfWeekList) {
            List<PScoreDaily> pScoreDailyList = scoreDailyDAO.listScoreDailyByUserNameAndNumOfWeek(userName, numOfWeek);
            Map<String, String> map1 = new LinkedHashMap<>();
            for (PScoreDaily pScoreDaily : pScoreDailyList) {
                map1.put(pScoreDaily.getWeek(), pScoreDaily.getScore());
            }
            map.put(numOfWeek, map1);
        }
        return map;
    }

    @Override
    public Map<String, List<VKnowledge>> mapKnowledge(String userName) {
        Map<String, List<VKnowledge>> map = new LinkedHashMap<>();
        List<String> numOfWeekList = knowledgeDAO.listNumOfWeek(userName);
        for (String numOfWeek : numOfWeekList) {
            List<PKnowledge> pKnowledgeList = knowledgeDAO.listKnowledge(userName, numOfWeek);
            List<VKnowledge> vKnowledgeList = copyPKnowledge2VKnowledge(pKnowledgeList);
            map.put(numOfWeek, vKnowledgeList);
        }
        return map;
    }

    private List<VScoreDaily> copyPScoreDaily2VScoreDaily(List<PScoreDaily> pScoreDailyList) {
        if (pScoreDailyList == null) {
            return null;
        }
        List<VScoreDaily> vScoreDailyList = new ArrayList<>();
        for (PScoreDaily pScoreDaily : pScoreDailyList) {
            VScoreDaily vScoreDaily = new VScoreDaily();
            BeanUtils.copyProperties(pScoreDaily, vScoreDaily);
            vScoreDailyList.add(vScoreDaily);
        }
        return vScoreDailyList;
    }

    private List<VKnowledge> copyPKnowledge2VKnowledge(List<PKnowledge> pKnowledgeList) {
        if (pKnowledgeList == null) {
            return null;
        }
        List<VKnowledge> vKnowledgeList = new ArrayList<>();
        for (PKnowledge pKnowledge : pKnowledgeList) {
            VKnowledge vKnowledge = new VKnowledge();
            BeanUtils.copyProperties(pKnowledge, vKnowledge);
            vKnowledgeList.add(vKnowledge);
        }
        return vKnowledgeList;
    }
}
