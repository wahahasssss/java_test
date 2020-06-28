package com.hdu.distributelock;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.UUID;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/7/31
 * @Time 下午8:06
 */
public class MyDistributeLock {

    private JedisPool pool;

    public MyDistributeLock(JedisPool pool) {
        this.pool = pool;
    }

    public String acquireLock() throws InterruptedException {
        Jedis conn = pool.getResource();
        String id = UUID.randomUUID().toString();
        while (true){
            if (conn.setnx("resource",id)==1){
                conn.expire("resource",5);
                return id;
            }

            Thread.sleep(1000);

        }
    }



    public void releaseLock(String id){
        Jedis conn = pool.getResource();
        if (conn.get("resource").equals(id)){
            conn.del("resource");
        }

    }
}
