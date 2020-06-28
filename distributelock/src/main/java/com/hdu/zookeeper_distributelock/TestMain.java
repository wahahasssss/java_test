package com.hdu.zookeeper_distributelock;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/8/1
 * @Time 下午8:43
 */
public class TestMain {

    private static int n = 500;
    public static void secKill(){
        System.out.println(--n);
    }
    public static void main(String[] args){
        for (int i = 0;i< 1000;i++){
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    DistributeLock lock = null;
                    try {
                        lock = new DistributeLock("47.94.245.160:2181","test1");
                        lock.lock();
                        if (n>0){
                            secKill();
                        }
                        System.out.println(Thread.currentThread().getName()+ ":running" );
                    }catch (Exception e){
                        System.out.println("分布式锁 failed。。。 ");
                    }
                    finally {
                        if (lock!=null){
                            lock.unlock();
                        }
                    }
                }
            });
            t.start();
        }
    }
}
