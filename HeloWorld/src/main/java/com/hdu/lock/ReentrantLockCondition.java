package com.hdu.lock;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockCondition {

    private static ReentrantLock lock = new ReentrantLock();

    private static volatile Boolean state = true;
    private static volatile Integer count = 100;

    private static Condition condition1 = lock.newCondition();
    private static Condition condition2 = lock.newCondition();

    public static void desc1(){
        try {
            lock.lock();
            while (state){
                condition1.await();
            }
            if (count > 0){
                System.out.println("Thread " + Thread.currentThread().getName() +" count : " + count);

                count --;
            }
            state = true;
            condition2.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public static void desc2(){
        try {
            lock.lock();
            while (!state){
                condition2.await();
            }
            if (count > 0){
                System.out.println("Thread " + Thread.currentThread().getName() +" count : " + count);
                count --;
            }
            state = false;
            condition1.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args){
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (count > 0){
                    desc1();
                }

            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (count > 0){
                    desc2();
                }
            }
        });

        t1.start();
        t2.start();
    }
}
