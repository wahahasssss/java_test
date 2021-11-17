package com.hdu.service;

import com.hdu.mapper.StudentMapper;
import com.hdu.model.po.StudentsPo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/12/31
 * @Time 下午5:03
 */
@Service
public class StudentService {
    private StudentMapper studentMapper;

    public void setStudentMapper(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    public List<StudentsPo> listAllStudents() {
        return studentMapper.listAllStudents();
    }
}
