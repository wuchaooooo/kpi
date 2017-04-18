package com.wuchaooooo.kpi.dao;

import com.wuchaooooo.kpi.javabean.po.PHeadTeacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Created by wuchaooooo on 30/11/2016.
 */
@Mapper
@Repository
public interface THeadTeacherDAO {
    String TABLE_NAME = "t_head_teacher";

    @Select("select * from " + TABLE_NAME + " where userName = #{userName}")
    PHeadTeacher getHeadTeacherByUserName(@Param("userName") String userName);
}
