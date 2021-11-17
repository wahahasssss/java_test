package com.example.springboot_akka.com.hdu.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")
public class HelloController {

    @PostMapping(value = "/hello")
    public String helloAkka() {
        return "hello world";
    }
}
