package com.hdu.distributelock;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/7/31
 * @Time 下午7:03
 */
public class RedisDistributeLockMain {
    public static void main(String[] args){
        final Service service = new Service();
        for (int i = 0;i < 500;i++){
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    service.secKill();
                }
            });
            thread.start();
        }

    }
}
