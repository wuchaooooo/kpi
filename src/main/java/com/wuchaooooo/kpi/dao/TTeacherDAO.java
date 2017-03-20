package com.wuchaooooo.kpi.dao;

import com.wuchaooooo.kpi.javabean.po.PTeacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Created by wuchaooooo on 30/11/2016.
 */
@Mapper
@Repository
public interface TTeacherDAO {
    String TABLE_NAME = "t_teacher";

    @Select("select * from " + TABLE_NAME + " where userId = #{userId} and password = #{password}")
    PTeacher getTeacherByUserIdAndPassword(@Param("userId") String userId, @Param("password") String password);
}
