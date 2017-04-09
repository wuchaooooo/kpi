package com.wuchaooooo.kpi.dao;

import com.wuchaooooo.kpi.javabean.po.PFeedback;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wuchaooooo on 22/02/2017.
 */
@Mapper
@Repository
public interface TFeedbackDAO {
    String TABLE_NAME = "t_feedback";

    @Select("select * from " + TABLE_NAME + " where `studentId` = #{studentId}")
    List<PFeedback> getFeedbacksByStudentId(@Param("studentId") Integer studentId);

    @Select("select * from " + TABLE_NAME + " where `id` = #{feedbackId}")
    PFeedback getFeedbackById(@Param("feedbackId") Integer feedbackId);

    @Insert("insert into " + TABLE_NAME + " (`studentId`, `createTime`, `modifyTime`, `type`, `content`) values (#{studentId}, #{createTime}, #{modifyTime}, #{type}, #{content})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    Integer insertFeedback(PFeedback pFeedback);

    @Update("update " + TABLE_NAME + " set `modifyTime` = #{modifyTime}, `content` = #{content} where id = #{id}")
    void updateFeedbackByFeedbackId(PFeedback pFeedback);

    @Delete("delete from " + TABLE_NAME + " where `id` = #{id}")
    void removeFeedBack(@Param("id") Integer id);
}
