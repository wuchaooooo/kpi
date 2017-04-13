package com.wuchaooooo.kpi.dao;

import com.wuchaooooo.kpi.javabean.po.PScoreDaily;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wuchaooooo on 13/04/2017.
 */
@Mapper
@Repository
public interface ScoreDailyDAO {
    String TABLE_NAME = "t_score_daily";

    @Select("select * from " + TABLE_NAME + " where `userName` = #{userName}")
    List<PScoreDaily> listScoreDaily(@Param("userName") String userName);

    //获得指定学生哪几周是有成绩的
    @Select("select numOfWeek from " + TABLE_NAME + " where `userName` = #{userName} group by `numOfWeek`")
    List<String> listNumOfWeek(@Param("userName") String userName);

    //获得指定学生指定周的成绩集合
    @Select("select `week`, `score` from " + TABLE_NAME + " where `userName` = #{userName} and `numOfWeek` = #{numOfWeek} order by `week`")
    List<PScoreDaily> listScoreDailyByUserNameAndNumOfWeek(@Param("userName") String userName, @Param("numOfWeek") String numOfWeek);
}
