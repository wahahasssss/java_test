package com.hdu.distributelock;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/7/30
 * @Time 下午9:21
 */
public class Service {
    private static JedisPool pool = null;

    static {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(6000);
        config.setMaxIdle(1000);
        config.setMaxWaitMillis(100000);
        config.setTestOnBorrow(true);
        pool = new JedisPool(config,"47.94.245.160",6379,3000);
    }

    DistributeLockRedis distributeLock = new DistributeLockRedis(pool);

    MyDistributeLock lock = new MyDistributeLock(pool);

    int n = 500;

    public void secKill(){
        String identifier = distributeLock.lockWithTimeout("resource",500000L,500000);
        System.out.println(Thread.currentThread().getName() + ":get the lock");
        System.out.println(--n);
        distributeLock.releaseLock("resource",identifier);
    }

    public void secKill2() throws InterruptedException {
        String id = lock.acquireLock();
        if (n<=0){
            System.out.println("there is no kill");
            return;
        }
        System.out.println(Thread.currentThread().getName() + ":get the lock");
        System.out.println(--n);
        lock.releaseLock(id);
    }


}
