package com.hdu.consul.register;

import com.google.common.net.HostAndPort;
import com.orbitz.consul.Consul;
import com.orbitz.consul.KeyValueClient;
import com.orbitz.consul.model.kv.Value;
import com.orbitz.consul.model.session.Session;
import com.sun.org.apache.bcel.internal.generic.RET;
import jdk.nashorn.internal.runtime.options.Option;
import sun.security.krb5.internal.PAData;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/3/14
 * @Time 下午8:07
 */
public class KeyValueService {

    private static Integer number = 500;
    private static final KeyValueClient KEY_VALUE_CLIENT;

    static {

        KEY_VALUE_CLIENT = Consul.builder().withHostAndPort(HostAndPort.fromParts(Constant.CONSUL_IP, Constant.CONSUL_PORT)).build().keyValueClient();
    }

    public static boolean putKv(String path, String value) {
        return KEY_VALUE_CLIENT.putValue(path, value);
    }

    public static void delKv(String path) {
        KEY_VALUE_CLIENT.deleteKey(path);
    }

    public static boolean exits(String path) {
        Optional<Value> res = KEY_VALUE_CLIENT.getValue(path);
        return res.isPresent();
    }

    public static String getKv(String path) {
        Optional<String> res = KEY_VALUE_CLIENT.getValueAsString(path);
        return res.isPresent() ? res.get() : "";
    }


    public static Boolean acquire(String key, String value, String sessionId) throws InterruptedException {
        while (true) {
            Boolean result = KEY_VALUE_CLIENT.acquireLock(key, value, sessionId);
            if (result) {
                System.out.println("acquire success");
                return true;
            } else {
                Thread.sleep(1000);
            }

        }

    }

    public static Boolean release(String key, String sessionId) {
        Boolean result = KEY_VALUE_CLIENT.releaseLock(key, sessionId);
        if (result) {
            System.out.println("release success");
        } else {
            System.out.println("release failed");
        }
        return result;
    }


    public static void main(String[] args) throws InterruptedException {
        KEY_VALUE_CLIENT.putValue("node/master/one", "one");
        KEY_VALUE_CLIENT.putValue("node/master/two", "two");
        List<String> keys = KEY_VALUE_CLIENT.getKeys("node/master");
        System.out.println(keys);
//        String sessionId = SessionService.acquireLock("mysession");
//        String value = UUID.randomUUID().toString();
//        acquire("lock3","111111",sessionId);
//        Thread.sleep(100000);
//        for (int i = 0;i< 100;i++){
//            Thread thread = new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    String sessionId = SessionService.acquireLock("mysession");
//                    String value = UUID.randomUUID().toString();
//                    try {
//                        if (acquire("lock",value,sessionId)){
//                            number = number - 1;
//                            System.out.println(String.format("number is : %d",number));
//                        }
//                        release("lock",sessionId);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//
//
//                }
//            });
//            thread.start();
//        }

    }
}
