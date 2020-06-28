package com.hdu.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2019/1/23
 * @Time 下午7:59
 */
@RestController
@RequestMapping(value = "/v1")
public class HeadersController {

    @RequestMapping(value = "/headers",method = {RequestMethod.POST,RequestMethod.GET})
    public HashMap<Object,Object> headers(HttpServletRequest request){
        HashMap<Object,Object> maps = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        Random random = new Random();
        while (headerNames.hasMoreElements()){
            String name = headerNames.nextElement();
            maps.put(name, request.getHeader(name));
        }
        maps.put("ts",String.valueOf(System.currentTimeMillis())
                +String.valueOf(random.nextInt(100000)));
        return maps;
    }
}
