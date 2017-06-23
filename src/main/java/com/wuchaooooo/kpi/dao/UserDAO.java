package com.wuchaooooo.kpi.dao;

import com.wuchaooooo.kpi.javabean.po.PUser;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wuchaooooo on 09/04/2017.
 */
@Mapper
@Repository
public interface UserDAO {
    String TABLE_NAME = "t_user";

    @Select("select * from " + TABLE_NAME)
    List<PUser> listUser();

    @Select("select * from " + TABLE_NAME + " where `id` = #{userId}")
    PUser getUserByUserId(long userId);

    @Select("select * from " + TABLE_NAME + " where `userName` = #{userName}")
    PUser getUserByUserName(String userName);

    @Select("select * from " + TABLE_NAME + " where `userName` = #{userName} and `password` = #{password}")
    PUser getUserByUserNameAndPassword(@Param("userName") String userName, @Param("password") String password);

    @Update("update " + TABLE_NAME +" set password = #{password} where userName = #{userName}")
    int modifyPassword(@Param("password") String password, @Param("userName") String userName);

    @Delete("delete from " + TABLE_NAME + " where `id` = #{userId}")
    int removeUser(long userId);

}
