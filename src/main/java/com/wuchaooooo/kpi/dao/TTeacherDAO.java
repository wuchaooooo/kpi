package com.wuchaooooo.kpi.dao;

import com.wuchaooooo.kpi.javabean.po.PTeacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * Created by wuchaooooo on 28/03/2017.
 */
@Mapper
@Repository
public interface TTeacherDAO {
    static final String TABLE_NAME = "t_teacher";

    @Select("select * from " + TABLE_NAME + " where userId = #{userId} and password = #{password}")
    PTeacher getTeacherByUserIdAndPassword(@Param("userId") String userId, @Param("password") String password);

    @Update("update " + TABLE_NAME + " set `mobile` = #{mobile}, `email` = #{email}")
    void updatePersonalInfo(PTeacher pTeacher);

    @Select("select * from " + TABLE_NAME + " where userId = #{userId}")
    PTeacher getTeacherByUserId(@Param("userId") String userId);

}
