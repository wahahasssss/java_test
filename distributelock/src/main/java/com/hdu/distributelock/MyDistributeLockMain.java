package com.hdu.distributelock;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/7/31
 * @Time 下午8:58
 */
public class MyDistributeLockMain {

    public static void main(String[] args) throws InterruptedException {
        final Service service = new Service();
        for (int i = 0; i < 1000; i++) {
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    try {
                        service.secKill2();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
        }
    }
}
