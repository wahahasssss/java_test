package com.hdu.interfaces;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/3/20
 * @Time 下午2:33
 */
public interface TriFunction<A,B,C,E> {
    E apply(A a,B b,C c);
    static void run(){
        System.out.println("this is a static method in interface");
    };

    default void defaultMethod(){
        System.out.println("this is a default instance method");
    };

}
