package com.hdu.interceptor;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2019/4/18
 * @Time 11:26 AM
 */
@Component
public class RateLimitInterceptor extends HandlerInterceptorAdapter {
    private static final RateLimiter rateLimiter = RateLimiter.create(1);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!rateLimiter.tryAcquire()) {
            System.out.println("限流中......");
            return false;
        }
        System.out.println("请求成功");
        return true;
    }
}
