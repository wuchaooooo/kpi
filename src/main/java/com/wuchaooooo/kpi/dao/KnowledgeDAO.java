package com.wuchaooooo.kpi.dao;


import com.wuchaooooo.kpi.javabean.po.PKnowledge;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wuchaooooo on 14/04/2017.
 */
@Mapper
@Repository
public interface KnowledgeDAO {
    String TABLE_NAME = "t_knowledge";

    @Select("select * from " + TABLE_NAME + " where `userName` = #{userName} and `numOfWeek` = #{numOfWeek}")
    List<PKnowledge> listKnowledge(@Param("userName") String userName, @Param("numOfWeek") String numOfWeek);

    //获得指定学生哪几周是有成绩的
    @Select("select numOfWeek from " + TABLE_NAME + " where `userName` = #{userName} group by `numOfWeek`")
    List<String> listNumOfWeek(@Param("userName") String userName);

}
