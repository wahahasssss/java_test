package com.hdu.service;

import com.hdu.dao.StudentMapper;
import com.hdu.model.StudentPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/12/30
 * @Time 下午7:39
 */
@Service
public class LoginService {

    @Autowired
    private StudentMapper studentMapper;

    public Boolean login(String userName, String password) {
        //获取数据库所有的学生信息
        List<StudentPo> studentPoList = studentMapper.listAllStudents();
        //获取对应某个学生的信息 根据名字查找
        StudentPo studentPo = studentMapper.getStudent(userName);

        //获取某个学生的密码  根据名字查找
        String passWord = studentMapper.getStudentPassword(userName);
        return true;
    }
}
