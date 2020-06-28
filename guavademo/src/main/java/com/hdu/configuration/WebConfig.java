package com.hdu.configuration;

import com.hdu.interceptor.RateLimitInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2019/4/18
 * @Time 11:49 AM
 */
@Configuration
public class WebConfig implements WebMvcConfigurer{
    @Autowired
    private RateLimitInterceptor rateLimitInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(rateLimitInterceptor);
    }
}
