package com.wuchaooooo.kpi.dao;

import com.wuchaooooo.kpi.javabean.po.PClazz;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wuchaooooo on 29/03/2017.
 */
@Mapper
@Repository
public interface TClazzDAO {
    String TABLE_NAME = "t_class";

    @Select("select `classType` from " + TABLE_NAME + " group by `classType`")
    List<String> listCalzzType();

    @Select("select * from " + TABLE_NAME + " where `className` = #{className}")
    PClazz getClazzByClazzName(@Param("className") String className);

    @Select("select * from " + TABLE_NAME + " where `headteacher` = #{headTeacher}")
    PClazz getClassByHeadTeacher(@Param("headTeacher") String headTeacher);

//    @Select("select * from " + TABLE_NAME + " where classType = #{clazzType} and classNo = #{clazzNo}")

}
