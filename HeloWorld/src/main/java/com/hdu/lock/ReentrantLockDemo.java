package com.hdu.lock;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {
    private static volatile Integer count = 10000;
    private static ReentrantLock lock = new ReentrantLock();

    public static void decrease(){
        try {
            lock.lockInterruptibly();
            Thread.sleep(10000);
            count --;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public static void main(String[] args){
        ThreadPoolExecutor pool = new ThreadPoolExecutor(10,10,10, TimeUnit.MINUTES,new LinkedBlockingQueue<>());
        for (int i = 0; i < 10000;i++){
            pool.submit(new Runnable() {
                @Override
                public void run() {
                    decrease();
                    System.out.println("count:" + count);
                }
            });
        }

        pool.shutdown();
        try {
            pool.awaitTermination(10,TimeUnit.MINUTES);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("the count is " + count);
    }
}
