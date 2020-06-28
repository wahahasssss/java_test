package com.hdu;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/5/24
 * @Time 下午4:13
 */
public class Main {
    static Integer count = 100;
    static List<String> apis = Arrays.asList("http://www.boboc.club:8080/api/headers");
//            "https://tenapi.cn/tel/?tel=15558071165",
//            "https://tcc.taobao.com/cc/json/mobile_tel_segment.htm?tel=15558071165");
//    static List<String> apis = Arrays.asList("http://www.boboc.club:8080/api/headers");
    public static void main(String[] args) throws IOException, KeyManagementException, NoSuchAlgorithmException, CertificateException, KeyStoreException, InterruptedException {
//        HttpResultDemoUtil.doHttpPost("http://www.boboc.club:8080/api/headers");
//        HttpResultDemoUtil.doHttpsPost("https://www.boboc.club:8343/api/headers");
//        HttpResultDemoUtil.doHttpsPost1("https://www.boboc.club:8343/api/headers");
//        doTest1();
        Random random = null;
        System.out.println(String.format("%d%s,TS:%d",null,random,System.currentTimeMillis()));
//        doPoolTest();
//        doTest2();
    }

    public static void doTest2() throws InterruptedException {
        final Random random = new Random();
        long start = System.currentTimeMillis();
        BlockingQueue queue = new LinkedBlockingQueue();
        ThreadPoolExecutor pool = new ThreadPoolExecutor(100, 100, 10, TimeUnit.SECONDS, queue);
        for (int i = 0;i < count;i++){
            pool.execute(new Runnable() {
                public void run() {
                    try {
                        int index = random.nextInt(apis.size());
                        HttpConnectionPoolUtil.post(apis.get(index),new HashMap<String, String>());
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
        }
        pool.shutdown();
        pool.awaitTermination(10,TimeUnit.HOURS);
        long end = System.currentTimeMillis();

        System.out.println(String.format("request 222 finished : " + (end - start)));
    }
    public static void doTest1() throws InterruptedException {
        final Random random = new Random();
        long start = System.currentTimeMillis();
        BlockingQueue queue = new LinkedBlockingQueue();
        ThreadPoolExecutor pool = new ThreadPoolExecutor(100, 100, 10000, TimeUnit.MILLISECONDS, queue);
        for (int i = 0;i < count;i++){
            pool.execute(new Runnable() {
                public void run() {
                    try {
                        int index = random.nextInt(apis.size());
                        System.out.println(HttpResultDemoUtil.doHttpPost(apis.get(index)));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        pool.shutdown();
        pool.awaitTermination(10,TimeUnit.HOURS);
        long end = System.currentTimeMillis();


        System.out.println(String.format("test1 request finished : " + (end - start)));
    }


    public static void doPoolTest() throws InterruptedException {
        final Random random = new Random();
        long start = System.currentTimeMillis();
        BlockingQueue queue = new LinkedBlockingQueue();
        ThreadPoolExecutor pool = new ThreadPoolExecutor(100, 100, 10, TimeUnit.SECONDS, queue);
        for (int i = 0;i < count;i++){
            pool.execute(new Runnable() {
                public void run() {
                    int index = random.nextInt(apis.size());
                    HttpConnectPoolUtil.doHttpPost(apis.get(index));
                }
            });
        }
        pool.shutdown();
        pool.awaitTermination(1000,TimeUnit.HOURS);
        long end = System.currentTimeMillis();
        System.out.println(String.format("pool request finished : " + (end - start)));
    }

}
