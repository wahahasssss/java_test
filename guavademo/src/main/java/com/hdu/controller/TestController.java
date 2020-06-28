package com.hdu.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2019/4/18
 * @Time 11:23 AM
 */
@RestController
@RequestMapping(value = "/api")
public class TestController {
    @RequestMapping(value = "ts",method = {RequestMethod.GET,RequestMethod.POST})
    public Long ts(){
        return System.currentTimeMillis();
    }
}
