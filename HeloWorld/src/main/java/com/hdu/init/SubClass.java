package com.hdu.init;

/**
 * @Author: ssf
 * @Date: 2020/4/9 11:19 上午
 */
public class SubClass extends SuperClass implements SuperInteger {
    public static String name = "SUB_CLASS";

    static {
        System.out.println("SubClass init");
    }

}
