package com.example.springapidemo.controller;

import com.alibaba.fastjson.JSON;
import com.example.springapidemo.CommonResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2019/3/12
 * @Time 6:24 PM
 */
@RestController
public class HomeController {
    @RequestMapping(value = "")
    public String home(){
        return "success";
    }


    @RequestMapping(value = "/metadata/reportIncrement",produces = "application/json;charset=utf-8")
    public CommonResult test(@RequestBody String object){
        System.out.println(object);
        return CommonResult.success();
    }
}
