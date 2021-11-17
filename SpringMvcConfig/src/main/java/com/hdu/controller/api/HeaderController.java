package com.hdu.controller.api;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/5/24
 * @Time 下午1:58
 */
@RestController
@RequestMapping(value = "/api")
public class HeaderController {
    @RequestMapping(value = "headers", method = {RequestMethod.POST, RequestMethod.GET})
    @ApiOperation("zzzzf")
    public HashMap<String, String> headers(HttpServletRequest request) {
        Enumeration<String> names = request.getHeaderNames();
        HashMap<String, String> headers = new HashMap<>();
        while (names.hasMoreElements()) {
            String key = names.nextElement();
            headers.put(key, request.getHeader(key));
        }
        return headers;
    }
}
