package com.hdu.drools.config;

import org.springframework.context.annotation.Bean;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2019/3/25
 * @Time 10:39 AM
 */
@org.springframework.context.annotation.Configuration
public class Configuration {
    @Bean
    public String testBean(){
        return "test";
    }
}
