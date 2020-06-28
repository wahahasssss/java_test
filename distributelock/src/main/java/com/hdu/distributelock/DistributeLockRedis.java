package com.hdu.distributelock;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;
import redis.clients.jedis.exceptions.JedisException;

import java.util.List;
import java.util.UUID;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/7/30
 * @Time 下午8:46
 */
public class DistributeLockRedis {
    private final JedisPool jedisPool;

    public DistributeLockRedis(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public String lockWithTimeout(String localName,Long acquireTimeout,long timeout){
        Jedis conn = null;
        String retIdentifier = null;
        try {
            conn = jedisPool.getResource();
            String identifier = UUID.randomUUID().toString();
            String lockKey = "lock:" + localName;
            int lockExpire = (int)(timeout/1000);
            long end = System.currentTimeMillis() + acquireTimeout;
            while (System.currentTimeMillis()<end){
                //setnx(key,value) 只有在key不存在时设置超市时间 SET if Not eXists
                if (conn.setnx(lockKey,identifier) == 1){
                    conn.expire(lockKey,lockExpire);
                    retIdentifier = identifier;
                    return retIdentifier;
                }

                /**
                 * 如果Redis的key没有设置超时时间，conn.ttl(key)会返回-1，当key不存在时会返回-2
                 */
                if (conn.ttl(lockKey) == -1){
                    conn.expire(lockKey,lockExpire);
                }
                try {
                    Thread.sleep(1000);
                }catch (Exception ex){
                    Thread.currentThread().interrupt();
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            if (conn!=null){
                conn.close();
            }
        }
        return retIdentifier;
    }

    public boolean releaseLock(String lockName,String identifier){
        Jedis conn = null;
        String lockKey = "lock:"+lockName;
        boolean retFlag = false;
        try {
            conn = jedisPool.getResource();
            while (true){
                conn.watch(lockKey);
                if (identifier.equals(conn.get(lockKey))){
                    Transaction transaction = conn.multi();
                    transaction.del(lockKey);
                    List<Object> results = transaction.exec();
                    if (results == null){
                        continue;
                    }
                    retFlag = true;
                }
                conn.unwatch();
                break;
            }
        }catch (JedisException ex){
            ex.printStackTrace();
        }finally {
            if (conn != null){
                conn.close();
            }
        }
        return retFlag;
    }
}
