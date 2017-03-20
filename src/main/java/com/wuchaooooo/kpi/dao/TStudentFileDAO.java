package com.wuchaooooo.kpi.dao;

import com.wuchaooooo.kpi.javabean.po.PStudentFile;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wuchaooooo on 22/02/2017.
 */
@Mapper
@Repository
public interface TStudentFileDAO {
    String TABLE_NAME = "t_student_file";

    @Insert("insert into " + TABLE_NAME + " (`studentId`, `name`, `type`, `path`, `createTime`) values (#{studentId}, #{name}, #{type}, #{path}, #{createTime})")
    void insertStudentFile(PStudentFile pStudentFile);

    @Select("select * from " + TABLE_NAME + " where `studentId` = #{studentId}")
    List<PStudentFile> getStudentFilesByStudentId(@Param("studentId") Integer studentId);

    @Select("select * from " + TABLE_NAME + " where id = #{fileId}")
    PStudentFile getSthdentFileByFileId(@Param("fileId") Integer fileId);
}
