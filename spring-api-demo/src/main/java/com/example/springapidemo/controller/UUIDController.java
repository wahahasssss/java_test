package com.example.springapidemo.controller;

import com.example.springapidemo.controller.randomId.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2019/8/1
 * @Time 3:13 PM
 */
@RestController
@RequestMapping(value = "/uuid")
public class UUIDController {

    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

    @RequestMapping(value = "/random")
    public long uuid(){
        return snowflakeIdWorker.nextId();
    }
}
