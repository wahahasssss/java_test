package com.hdu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/12/31
 * @Time 下午5:59
 */
@Controller
public class HomeController {
    @RequestMapping(value = "/home")
    public String home(){
        return "login";
    }
}
