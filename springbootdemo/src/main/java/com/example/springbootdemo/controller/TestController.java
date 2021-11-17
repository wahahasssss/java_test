package com.example.springbootdemo.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/4/17
 * @Time 下午2:18
 */
@RestController
@RequestMapping(value = "/api")
@ResponseBody
public class TestController {

    @RequestMapping(value = "headers", method = {RequestMethod.POST, RequestMethod.GET})
    public HashMap<String, String> headers(HttpServletRequest request) {
        Enumeration<String> names = request.getHeaderNames();
        HashMap<String, String> headers = new HashMap<>();
        while (names.hasMoreElements()) {
            String key = names.nextElement();
            headers.put(key, request.getHeader(key));
        }
        headers.put("ts", String.valueOf(System.currentTimeMillis()));
        return headers;
    }
}
