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

    @Select("select * from " + TABLE_NAME + " where userId = #{userId} and password = #{password}")
    PHeadTeacher getHeadTeacherByUserIdAndPassword(@Param("userId") String userId, @Param("password") String password);
}
