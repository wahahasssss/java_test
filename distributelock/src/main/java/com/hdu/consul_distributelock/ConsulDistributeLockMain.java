package com.hdu.consul_distributelock;



import com.google.common.net.HostAndPort;
import com.orbitz.consul.Consul;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/8/7
 * @Time 下午4:07
 */
public class ConsulDistributeLockMain {



    public static volatile ConcurrentHashMap<String,ArrayBlockingQueue<Long>> ipLoginDetails = new ConcurrentHashMap<>();
    private static Integer number = 500;
    public static void main(String[] args){
        ArrayBlockingQueue<Long> queue = new ArrayBlockingQueue<Long>(30);
        List<Integer> s = new ArrayList<>();
       queue.offer(1L);
       String s = "fafwefwa";
        HashSet<String> set = new HashSet<>();
        set.parallelStream().sorted().collect(Collectors.toList())
        Long currentTime = System.currentTimeMillis();
        for (int i = 0;i<100;i++){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    Consul consul = Consul.builder().withHostAndPort(HostAndPort.fromParts("127.0.0.1",8414))
                            .build();
                    DistributeLockConsul lock = new DistributeLockConsul(consul,"lock-session","lock-key");
                    try {
                        if (lock.lock(true)){
                            --number;
                            System.out.println(String.format("thread name:%s get lock,number is %d",Thread.currentThread().getName(),number));
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }finally {
                        lock.releaseLock();
                    }

                }
            });
            thread.start();
        }
    }
}
