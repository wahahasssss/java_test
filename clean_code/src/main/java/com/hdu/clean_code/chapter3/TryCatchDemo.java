package com.hdu.clean_code.chapter3;

/**
 * @Author: ssf
 * @Date: 2020/3/30 5:17 下午
 */
public class TryCatchDemo {

    public static void main(String[] args){
        try {
            doSomething();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void doSomething(){
        //todo do something
    }
}
