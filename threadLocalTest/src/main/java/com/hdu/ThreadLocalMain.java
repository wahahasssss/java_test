package com.hdu;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/8/2
 * @Time 上午11:45
 */
public class ThreadLocalMain {
    private static ThreadLocal<String> local1 = new ThreadLocal<String>();
    private static ThreadLocal<String> local2 = new ThreadLocal<String>();

    private static AtomicInteger integer = new AtomicInteger(32);

    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(new Runnable() {
            public void run() {
//                local1.set("thread1 one");
//                local2.set("thread1 two");

                while (true){
                    System.out.println(local1.get());
//                    System.out.println(local2.get());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        thread1.start();

        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                local1.set("thread 2 local1 one");
                local1.set("thread 2 local1 two");
                local2.set("thread 2 local2 one");
                while (true){
                    System.out.println(local1.get());
                    System.out.println(local2.get());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        local1.remove();
                        local2.remove();
                    }
                }
            }
        },"Thread2");

        thread2.start();

        while (true){
            Thread.sleep(1000);
        }
    }
}
