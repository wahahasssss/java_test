package com.hdu.consul.register;

import com.alibaba.fastjson.JSON;
import com.google.common.net.HostAndPort;
import com.hdu.consul.CustomBlackListingConsulFailoverStrategy;
import com.orbitz.consul.Consul;
import com.orbitz.consul.KeyValueClient;
import com.orbitz.consul.cache.ConsulCache;
import com.orbitz.consul.cache.KVCache;
import com.orbitz.consul.model.kv.Value;
import com.orbitz.consul.option.PutOptions;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2019/2/20
 * @Time 下午8:34
 */
public class KeyValueMain {


    /**
     * 添加consul的KeyValue监听，
     *
     * @param key
     */
    private static KVCache addKeyValueListener(String key, KeyValueClient client) {
        KVCache kvCache = KVCache.newCache(client, key);
        kvCache.addListener(new ConsulCache.Listener<String, Value>() {
            @Override
            public void notify(Map<String, Value> map) {
                System.out.println(JSON.toJSONString(map));
            }
        });
        return kvCache;
    }

    public static void main(String[] args) throws InterruptedException {
        List<HostAndPort> hostAndPortList = new ArrayList<>();
        hostAndPortList.add(HostAndPort.fromParts("127.0.0.1", 8414));
        hostAndPortList.add(HostAndPort.fromParts("127.0.0.1", 8414));
        CustomBlackListingConsulFailoverStrategy s = new CustomBlackListingConsulFailoverStrategy(hostAndPortList, 1000L);

//        Consul.builder().withHostAndPort(HostAndPort.fromParts("127.0.0.1",8414)).build();
        Consul consul = Consul.builder().withMultipleHostAndPort(hostAndPortList, 1000L)
                .withFailoverInterceptor(s)
                .build();
//        ThreadPoolExecutor pool = new ThreadPoolExecutor(10, 10, 4, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1000000));

//        for (int i = 0;i < 1000000;i++)
//            pool.execute(new Runnable() {
//                @Override
//                public void run() {
//                    HashMap<String,Object> map = new HashMap<>();
//                    map.put("ts",System.currentTimeMillis());
//                    map.put("r",new Random().nextInt(10000));
//                    String value = JSON.toJSONString(map);
//                    consul.keyValueClient().putValue("/test/value", value);
//                    if (consul.keyValueClient().getValue("/test/value").equals(value)){
//                        System.out.println("success");
//                    }else {
//                        System.out.println("failed");
//                    }
//                }
//            });

        int success = 0;
        int failed = 0;

        KeyValueClient keyValueClient = consul.keyValueClient();

        KVCache kvCache = addKeyValueListener("/listener/key", keyValueClient);
        kvCache.start();
        for (int i = 0; i < 3; i++) {

            String key = "/test/value";
            HashMap<String, Object> map = new HashMap<>();
            map.put("ts", System.currentTimeMillis());
            map.put("r", new Random().nextInt(10000));
            String value = JSON.toJSONString(map);
            keyValueClient.putValue(key, value);

//            PutOptions putOptions = PutOptions.BLANK;
//
//            consul.keyValueClient().putValue(PutOptions.)
            String consulValue = consul.keyValueClient().getValueAsString(key).get();
            if (consulValue.equals(value)) {
                success++;
                System.out.println(String.format("i is %d,success times is %d", i, success));
            } else {
                failed++;
                System.out.println(String.format("i is %d,failed times is %d", i, failed));
            }
//            consul.keyValueClient().deleteKey(key);
        }

        while (true) {
            keyValueClient.putValue("/listener/key", String.valueOf(System.currentTimeMillis()));
            Thread.sleep(1000);
        }


    }


}
