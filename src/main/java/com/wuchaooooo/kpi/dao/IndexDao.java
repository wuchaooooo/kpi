package com.wuchaooooo.kpi.dao;

import com.wuchaooooo.kpi.javabean.po.PUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * Created by wuchaooooo on 24/11/2016.
 */
@Mapper
@Repository
public interface IndexDao {
    @Select("select `id`, `userId`, `password`, `realName` from ${tableName} where `userId` = #{userId} and `password` = #{password}")
    PUser loginConfirm(@Param("tableName") String tableName, @Param("userId") String userId, @Param("password") String password);

    @Update("update ${tableName} set password = #{password} where userId = #{userId}")
    void changePassword(@Param("tableName") String tableName, @Param("password") String password, @Param("userId") String userId);
}
