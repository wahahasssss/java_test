package com.hdu.reentrantlock;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: ssf
 * @Date: 2020/4/10 10:24 上午
 */
public class ReentrantLockNoFairTest {
    private static ReentrantLock lock = new ReentrantLock();
    private static Integer count = 0;

    public static void doSomething() {
        try {
            lock.lock();
            count++;
            System.out.println(String.format("Thread %s do something %d", Thread.currentThread().getName(), count));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(10, 10, 1000, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        for (int i = 0; i < 10000; i++) {
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    doSomething();
                }
            });
        }
        pool.shutdown();
        try {
            pool.awaitTermination(100, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
