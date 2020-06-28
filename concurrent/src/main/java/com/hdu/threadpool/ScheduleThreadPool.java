package com.hdu.threadpool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2019/1/29
 * @Time 上午11:28
 */
public class ScheduleThreadPool {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setNameFormat("task-scanner-executor-%d")
                .setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                    public void uncaughtException(Thread t, Throwable e) {
                        System.out.println(e.getCause());
                    }
                }).build();
        ScheduledThreadPoolExecutor scheduledPool = new ScheduledThreadPoolExecutor(100,threadFactory);
        ScheduledFuture future = scheduledPool.scheduleAtFixedRate(new Runnable() {
            public void run() {
                System.out.println("begin");
//                try {
//                    System.out.println(1/0);
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
                System.out.println(1/0);
            }
        },1,10, TimeUnit.SECONDS);
        System.out.println(future.get());
        future.cancel(true);
    }
}
