package com.hdu.springboot_k8s.controller;

import com.hdu.springboot_k8s.model.ResultDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName：K8sControler
 * @Description：TODO
 * @Author：ssf
 * @Date：2021/8/3 10:29 下午
 * @Versiion：1.0
 */
@RestController
public class K8sController {
    @RequestMapping(value = "/ts", method = {RequestMethod.GET, RequestMethod.POST})
    public ResultDto timestamp(){
        Map<String, String> data = new HashMap<>();
        data.put("ts", String.valueOf(System.currentTimeMillis()));
        return ResultDto.builder().code(0).data(data).msg("success").build();
    }
}