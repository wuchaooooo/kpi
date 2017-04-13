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
    String TABLE_NAME = "t_teacher";

    @Update("update " + TABLE_NAME + " set `mobile` = #{mobile}, `email` = #{email}")
    void updatePersonalInfo(PTeacher pTeacher);

    @Select("select * from " + TABLE_NAME + " where `userName` = #{userName}")
    PTeacher getTeacherByUserName(@Param("userName") String userName);

}
