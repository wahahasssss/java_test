package com.hdu.controller;

import com.hdu.model.dto.ResultDto;
import com.hdu.model.dto.UserDto;
import com.hdu.service.ILoginService;
import com.hdu.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/12/31
 * @Time 下午3:53
 */
@Controller
@RequestMapping(value = "/v1")
public class LoginController {

    private StudentService studentService;
    private ILoginService loginService;

    public void setLoginService(ILoginService loginService) {
        this.loginService = loginService;
    }

    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResultDto login(@RequestBody UserDto params) {
        ResultDto result = new ResultDto();
        if (loginService.login(params.getName(), params.getPassword())) {
            result.setData(null);
            result.setMsg("success");
            result.setStatus(true);
            return result;
        } else {
            result.setData(null);
            result.setMsg("failed");
            result.setStatus(false);
            return result;
        }
    }

    @RequestMapping(value = "/listAllStudents", method = RequestMethod.GET)
    @ResponseBody
    public ResultDto listAllStudents(@RequestBody UserDto params) {
        ResultDto result = new ResultDto();
        result.setCode(200);
        result.setStatus(true);
        result.setMsg("success");
        result.setData(studentService.listAllStudents());
        return result;
    }

}
