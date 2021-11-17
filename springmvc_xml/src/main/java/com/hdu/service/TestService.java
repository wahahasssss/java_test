package com.hdu.service;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2019/2/14
 * @Time 下午3:47
 */
@Service
public class TestService {
    @PostConstruct
    public void init() {
        System.out.println("TestService init...");
    }
}
