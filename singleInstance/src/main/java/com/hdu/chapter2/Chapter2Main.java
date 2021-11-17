package com.hdu.chapter2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/9/27
 * @Time 下午7:23
 */
public class Chapter2Main {

    public static void main(String[] args) {
        BlockingQueue queue = new ArrayBlockingQueue(1000);
        ThreadPoolExecutor pool = new ThreadPoolExecutor(10, 10, 1000, TimeUnit.SECONDS, queue);

//        for (int i = 0;i < 1000;i++){
//            pool.execute(new Runnable() {
//                public void run() {
//                    Singleton singleton = Singleton.newInstance();
//                    System.out.println(singleton.toString());
//                }
//            });
//        }


        for (int i = 0; i < 1000; i++) {
            pool.execute(new Runnable() {
                public void run() {
                    Singleton singleton = Singleton.newInstanceDoubleCheck();
                    System.out.println(singleton.toString());
                }
            });
        }
    }
}
