package com.hdu.dao;

import com.hdu.model.StudentPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/12/30
 * @Time 下午7:40
 */
@Mapper
@Repository
public interface StudentMapper {
    @Select("SELECT * FROM students")
    List<StudentPo> listAllStudents();


    @Select("SELECT * FROM students WHERE username=${username}")
    StudentPo getStudent(@Param("username") String userName);


    @Select("SELECT password FROM students WHERE username=${username}")
    String getStudentPassword(@Param("username") String userName);
}
