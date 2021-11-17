package com.hdu.init;

/**
 * @Author: ssf
 * @Date: 2020/4/9 11:22 上午
 */
public class InitTest {

    static {
        System.out.println("InitTest init!");
    }


    public static void main(String[] args) {
        System.out.println(SubClass.value);
        System.out.println(SubClass.name);

        Thread thread = new Thread();
    }
}
