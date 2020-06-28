package com.hdu.zookeeper_distributelock;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/8/1
 * @Time 下午8:17
 */
public class LockException extends Exception{
    public LockException(Exception e) {
        super(e.getMessage());
    }
}
