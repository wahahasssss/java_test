package com.hdu.atomic;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: ssf
 * @Date: 2020/4/22 3:32 下午
 */
public class AtomicTestMain {


    public static void main(String[] args){
        AtomicInteger i = new AtomicInteger();
        i.getAndAdd(1);
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
    }
}
