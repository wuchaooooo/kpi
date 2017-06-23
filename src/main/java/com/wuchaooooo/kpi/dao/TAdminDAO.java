package com.wuchaooooo.kpi.dao;

import com.wuchaooooo.kpi.javabean.po.PAdmin;
import com.wuchaooooo.kpi.javabean.po.PHeadTeacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Created by wuchaooooo on 22/02/2017.
 */
@Mapper
@Repository
public interface TAdminDAO {
    String TABLE_NAME = "t_admin";

    @Select("select * from " + TABLE_NAME + " where userId = #{userId} and password = #{password}")
    PAdmin getAdminByUserIdAndPassword(@Param("userId") String userId, @Param("password") String password);

    @Select("select * from " + TABLE_NAME + " where userName = #{userName}")
    PAdmin getAdminByUserName(@Param("userName") String userName);
}
