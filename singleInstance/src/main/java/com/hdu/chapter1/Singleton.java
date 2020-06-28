package com.hdu.chapter1;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/9/27
 * @Time 下午7:11
 */
public class Singleton {
    private static Singleton singleton = new Singleton();
    private Singleton(){}
    public static Singleton newInstance(){
        return singleton;
    }
}
