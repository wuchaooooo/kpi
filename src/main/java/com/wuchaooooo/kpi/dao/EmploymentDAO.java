package com.wuchaooooo.kpi.dao;

import com.wuchaooooo.kpi.javabean.po.PEmployment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Created by wuchaooooo on 08/04/2017.
 */
@Mapper
@Repository
public interface EmploymentDAO {
    String TABLE_NAME = "t_employment";

    @Select("select * from " + TABLE_NAME + " where userId = #{userId}")
    PEmployment getEmploymentByUserId(@Param("userId") String userId);
}
