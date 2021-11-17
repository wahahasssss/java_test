package com.hdu.base;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2019/2/18
 * @Time 下午12:52
 */
public class ThreadTest {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(System.currentTimeMillis());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        System.out.println("main thread is end");
        System.out.println(Boolean.valueOf("t"));
    }
}
