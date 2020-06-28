package com.hdu.mutildatasource.controller;

import com.hdu.mutildatasource.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/8/2
 * @Time 上午11:51
 */
@RestController
@RequestMapping(value = "/api")
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping(value = "/db1",method = RequestMethod.POST)
    public String db1(){
        return testService.test1();
    }

    @RequestMapping(value = "/db2",method = RequestMethod.POST)
    public String db2(){
        return testService.test2();
    }
}
