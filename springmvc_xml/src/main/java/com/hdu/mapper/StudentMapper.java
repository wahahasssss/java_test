package com.hdu.mapper;

import com.hdu.model.po.StudentsPo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/12/31
 * @Time 下午4:52
 */
@Repository
public interface StudentMapper {
    List<StudentsPo> listAllStudents();

    StudentsPo getStudentByName(String username);
}
