package com.hdu.controller;

import com.hdu.model.dto.QueryParamDto;
import com.hdu.model.dto.ResultDto;
import com.hdu.util.ServiceResultUtil;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

/**
 * @ClassName：QueryController
 * @Description：查询控制器
 * @Author：ssf
 * @Date：2021/9/6 10:22 下午
 * @Versiion：1.0
 */
@RestController
@RequestMapping("/query")
public class QueryController {

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResultDto test() {
        return ServiceResultUtil.success();
    }


    @RequestMapping(value = "/sql", method = RequestMethod.POST)
    public ResultDto test(@RequestBody QueryParamDto param) {
        return ServiceResultUtil.success();
    }
}