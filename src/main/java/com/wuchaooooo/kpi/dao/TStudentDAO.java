package com.wuchaooooo.kpi.dao;

import com.wuchaooooo.kpi.javabean.po.PStudent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wuchaooooo on 26/11/2016.
 */
@Mapper
@Repository
public interface TStudentDAO {
    String TABLE_NAME = "t_student";

    @Select("select * from " + TABLE_NAME + " where `id` = #{id}")
    PStudent getStudentByStudentId(@Param("id") Integer id);

    @Select("select * from " + TABLE_NAME + " where `userId` = #{userId}")
    PStudent getStudentByUserId(@Param("userId") String userId);

    @Select("select * from " + TABLE_NAME + " where `userId` = #{userId} and `password` = #{password}")
    PStudent getStudentByUserIdAndPassword(@Param("userId") String userId, @Param("password") String password);

    @Update("update " + TABLE_NAME + " set `mobile` = #{mobile}, `email` = #{email}, `school` = #{school}, `major` = #{major} where `userId` = #{userId}")
    void updatePersonalInfo(PStudent pStudent);

    @Select("select * from " + TABLE_NAME + " where `className` = #{className}")
    List<PStudent> getClassmates(@Param("className") String className);
}
