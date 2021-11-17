package com.hdu.controller;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2019/4/19
 * @Time 4:03 PM
 */
@Controller("/api")
public class TestController {
    @Get("/")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello world";
    }
}
