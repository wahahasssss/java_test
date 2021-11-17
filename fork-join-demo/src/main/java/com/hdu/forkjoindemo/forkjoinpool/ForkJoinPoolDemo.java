package com.hdu.forkjoindemo.forkjoinpool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/8/28
 * @Time 下午3:52
 */
public class ForkJoinPoolDemo {


    public static void main(String[] args) throws InterruptedException {
        ForkJoinPool pool = new ForkJoinPool();


        for (int i = 0; i < 100; i++) {
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("print info:" + Thread.currentThread().getName());
                }
            });
        }

        pool.shutdown();
        pool.awaitTermination(20, TimeUnit.SECONDS);
        System.out.println("end");
    }
}
