package concurrency;

import java.util.Random;

/**
 * Created by CTWLPC on 2017/4/24.
 */
public class DeadLock implements Runnable {
    private static final Object resource1 = new Object();
    private static final Object resource2 = new Object();
    private final Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) {
        Thread myThread1 = new Thread(new DeadLock(), "thread-1");
        Thread myThread2 = new Thread(new DeadLock(), "thread-2");
        myThread1.start();
        myThread2.start();
        try {
            myThread1.join();
            myThread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(">>>>>>>>>>>>>>>>>>>>");
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            boolean b = random.nextBoolean();
            if (b) {
                System.out.println("[" + Thread.currentThread().getName() + "] Trying to lock resource 1.");
                synchronized (resource1) {
                    System.out.println("[" + Thread.currentThread().getName() + "] Locked resource 1.");
                    System.out.println("[" + Thread.currentThread().getName() + "] Trying to lock resource 2.");
                    synchronized (resource2) {
                        System.out.println("[" + Thread.currentThread().getName() + "] Locked resource 2.");
                    }
                }
            } else {
                System.out.println("[" + Thread.currentThread().getName() + "] Trying to lock resource 2.");
                synchronized (resource2) {
                    System.out.println("[" + Thread.currentThread().getName() + "] Locked resource 2.");
                    System.out.println("[" + Thread.currentThread().getName() + "] Trying to lock resource 1.");
                    synchronized (resource1) {
                        System.out.println("[" + Thread.currentThread().getName() + "] Locked resource 1.");
                    }
                }
            }
        }
    }
}
