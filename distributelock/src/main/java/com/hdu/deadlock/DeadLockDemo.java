package com.hdu.deadlock;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2019/8/1
 * @Time 5:53 PM
 */
public class DeadLockDemo {
    private static Object obj1 = new Object();
    private static Object obj2 = new Object();

    public static void lock1(){
        while (true){
            try {
                synchronized(obj1){
                    Thread.sleep(3000);
                    System.out.println("lock1 Lock object1");
                    synchronized (obj2){
                        System.out.println("lock1 Lock object2");
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }


    public static void lock2(){
        while (true){
            try {
                synchronized(obj2){
                    Thread.sleep(3000);
                    System.out.println("lock2 Lock object2");
                    synchronized (obj1){
                        System.out.println("lock2 Lock object1");
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }



    public static void main(String[] args){
        Thread thread1 = new Thread(DeadLockDemo::lock1);
        Thread thread2 = new Thread(DeadLockDemo::lock2);
        thread1.start();
//        thread2.start();
    }
}
