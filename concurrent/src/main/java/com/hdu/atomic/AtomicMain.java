package com.hdu.atomic;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2019/3/6
 * @Time 3:44 PM
 */
public class AtomicMain {
    private static AtomicInteger atomic = new AtomicInteger();


    public static void mutilatePreload() throws InterruptedException {
        BlockingQueue queues = new ArrayBlockingQueue(100000);
        ThreadPoolExecutor pool = new ThreadPoolExecutor(30, 30, 10, TimeUnit.SECONDS, queues);
        for (int i = 0; i < 2000; i++) {
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(atomic.incrementAndGet());
                    } catch (Exception e) {
                        e.printStackTrace();
                        if (e instanceof ConcurrentModificationException) {
                            System.out.println("======>>>>>");
                        }

                    }
                }
            });
        }

//        System.out.println(.);
        pool.shutdown();
        pool.awaitTermination(100, TimeUnit.SECONDS);
    }

    public static void main(String[] args) throws InterruptedException {
        mutilatePreload();
    }
}
