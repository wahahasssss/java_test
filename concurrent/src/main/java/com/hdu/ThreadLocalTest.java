package com.hdu;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/8/8
 * @Time 上午11:52
 */
public class ThreadLocalTest {
    ThreadLocal<MyStatusEntity> threadLocal;

    ThreadLocal<String> stringLocal = new ThreadLocal<String>(){
        @Override
        protected String initialValue() {
            return Thread.currentThread().getName();
        }
    };
    ThreadLocal<Long> longLocal = new ThreadLocal<Long>(){
        @Override
        protected Long initialValue() {
            return Thread.currentThread().getId();
        }
    };

    public void set(){
        longLocal.set(Thread.currentThread().getId());
        stringLocal.set(Thread.currentThread().getName());
    }

    public long getLong(){
        return longLocal.get();
    }

    public String getString(){
        return stringLocal.get();
    }
    public static void main(String[] args) throws InterruptedException {
        final ThreadLocalTest threadLocalTest = new ThreadLocalTest();
        threadLocalTest.set();
        System.out.println(threadLocalTest.getLong());
        System.out.println(threadLocalTest.getString());

        Thread thread = new Thread(new Runnable() {
            public void run() {

                System.out.println(threadLocalTest.getLong());
                System.out.println(threadLocalTest.getString());
                threadLocalTest.set();
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                System.out.println(threadLocalTest.getLong());
                System.out.println(threadLocalTest.getString());
            }
        });
        thread.start();
        thread.join();

        System.out.println("========================================");
        System.out.println(threadLocalTest.getLong());
        System.out.println(threadLocalTest.getString());
    }
}
