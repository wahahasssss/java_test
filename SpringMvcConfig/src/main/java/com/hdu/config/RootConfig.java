package com.hdu.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/5/24
 * @Time 下午1:50
 */
@Configuration
@ComponentScan(basePackages = {"com.hdu"},excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,value = EnableWebMvc.class)})
public class RootConfig {
}
