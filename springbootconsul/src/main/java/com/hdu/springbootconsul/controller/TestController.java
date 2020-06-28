package com.hdu.springbootconsul.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/8/8
 * @Time 下午5:27
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {

    @RequestMapping(value = "/test",method = {RequestMethod.POST,RequestMethod.GET})
    public String test(){
        return "test";
    }
}
