package com.hdu.reentrantlock;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: ssf
 * @Date: 2020/4/21 2:59 下午
 */
public class ReentrantLockFairTest {
    private static ReentrantLock fairLock = new ReentrantLock(true);
    private static volatile Integer count = 0;

    public static void doSomething() {
        try {
            fairLock.lock();
            count = count + 2;
            System.out.println(count);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            fairLock.unlock();
        }
    }


    //测试一些愚蠢的事情
    public static void doSomethingFool() {
        try {
            count = count + 2;
            System.out.println(count);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

    public static void main(String[] args) {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(10, 10, 1, TimeUnit.MINUTES, new LinkedBlockingQueue<>());
        for (int i = 0; i < 10000; i++) {
            pool.execute(new Runnable() {
                @Override
                public void run() {
//                    doSomethingFool();
                    doSomething();
                }
            });
        }
        pool.shutdown();
        try {
            pool.awaitTermination(10, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
