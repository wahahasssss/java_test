package com.example.springapidemo.controller.randomId;

import org.springframework.stereotype.Service;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2019/8/1
 * @Time 3:16 PM
 */
@Service
public class SnowflakeIdWorker {

    /**
     * 开始时间戳
     */
    private final long START_TIMESTAMP = 1564643843000L;


    /**
     * 机器id所占的位数
     */
    private final long workerIdBits = 5L;


    /**
     * 数据标识id所占的位数
     */
    private final long dataCenterIdBits = 5L;

    /**
     * 支持最大的机器id
     */
    private final long maxWorkerId = -1L ^ (-1L << workerIdBits); //(1111 1111 ^ (1111 1111 << 5) = 0001 1111 = 31)

    /**
     * 支持最大的数据标识id
     */
    private final long maxDataCenterId = -1L ^ (-1L << dataCenterIdBits);


    /**
     * 序列在id中所占的位数
     */
    private final long sequenceBits = 12L;

    /**
     * 机器id向左移12位
     */
    private final long workerIdShift = sequenceBits;


    /**
     * 数据标识id向左移17位
     */
    private final long datacenterIdShift = sequenceBits + workerIdBits;

    /**
     * 时间戳向左移22位
     */
    private final long timestampLeftShift = sequenceBits + workerIdBits + dataCenterIdBits;


    /**
     * 生成序列的掩码，4095
     */
    private final long sequenceMask = -1L ^ (-1L << sequenceBits);


    /**
     * 工作机器id
     */
    private long workerId;


    /**
     * 数据中心id
     */
    private long datacenterId;

    /**
     * 毫秒内序列
     */
    private long sequence = 0L;

    /**
     * 上次生成id的时间戳
     */
    private long lastTimeStamp = -1L;


    public SnowflakeIdWorker(){
        this(1,1);
    }

    public SnowflakeIdWorker(long workerId,long datacenterId){
        assert workerId > maxWorkerId || workerId < 0: "workerId must in 0 < 31";
        assert datacenterId > maxDataCenterId || datacenterId < 0: "datacenterId must in 0 < 31";
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }

    public synchronized long nextId(){
        long timeStamp = timeGen();
        assert timeStamp < lastTimeStamp:"时间戳错误，时间回滚";
        if (timeStamp == lastTimeStamp){
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0){
                timeStamp = tillNextMillis(lastTimeStamp);
            }
        }else{
            sequence = 0L;
        }
        lastTimeStamp = timeStamp;
        return ((timeStamp - START_TIMESTAMP) << timestampLeftShift) | (datacenterId << datacenterIdShift) | (workerId << workerIdShift) | sequence;
    }


    private long tillNextMillis(long lastTimeStamp){
        long timeStamp = timeGen();
        while (timeStamp <= lastTimeStamp){
            timeStamp = timeGen();
        }
        return timeStamp;
    }
    private long timeGen(){
        return System.currentTimeMillis();
    }
}
