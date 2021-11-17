package com.hdu;

import redis.clients.jedis.Jedis;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/7/30
 * @Time 下午8:46
 */
public class Main {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("47.94.245.160", 6379);
        System.out.println(jedis.ping());
    }
}
