package com.hdu.controller;

import com.hdu.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2019/5/7
 * @Time 2:48 PM
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {
    @Autowired
    private IService iService;


    @RequestMapping(value = "/print",method = {RequestMethod.GET,RequestMethod.POST})
    public boolean print(){
        iService.queryAllData();
        return true;
    }

    @RequestMapping(value = "/transaction", method = {RequestMethod.GET, RequestMethod.POST})
    public boolean transaction(){
        iService.transactionTest();
        return true;
    }

    @RequestMapping(value = "/read", method = {RequestMethod.GET, RequestMethod.POST})
    public boolean read(){
        iService.readAfterCommit();
        return true;
    }
}
