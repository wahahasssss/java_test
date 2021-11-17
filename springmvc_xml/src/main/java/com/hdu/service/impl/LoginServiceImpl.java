package com.hdu.service.impl;

import com.hdu.mapper.StudentMapper;
import com.hdu.service.ILoginService;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/12/31
 * @Time 下午5:42
 */
public class LoginServiceImpl implements ILoginService {
    private StudentMapper studentMapper;

    public void setStudentMapper(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    @Override
    public boolean login(String userName, String password) {
        if (studentMapper.getStudentByName(userName).getPassword().equals(password)) {
            return true;
        }
        return false;
    }
}
