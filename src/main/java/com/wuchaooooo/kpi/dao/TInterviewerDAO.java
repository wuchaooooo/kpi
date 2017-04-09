package com.wuchaooooo.kpi.dao;

import com.wuchaooooo.kpi.javabean.po.PInterviewer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Created by wuchaooooo on 30/11/2016.
 */
@Mapper
@Repository
public interface TInterviewerDAO {
    String TABLE_NAME = "t_interviewer";

    @Select("select * from " + TABLE_NAME + " where userId = #{userId} and password = #{password}")
    PInterviewer getInterviewerByUserIdAndPassword(@Param("userId") String userId, @Param("password") String password);
}
