package com.hdu;


import org.apache.http.*;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;

import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.InputStream;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/10/8
 * @Time 下午3:16
 */
public class HttpConnectPoolUtil {
    private static final int CONNECT_TIMEOUT = 3000;// 设置连接建立的超时时间为10s
    private static final int SOCKET_TIMEOUT = 3000;

    private static final int MAX_CONN = 4400; // 最大连接数
    private static final int Max_PRE_ROUTE = 32;
    public static PoolingHttpClientConnectionManager manager = null;
    public static ScheduledExecutorService scheduledPool = null;

    public static Object lock = new Object();
    public static volatile CloseableHttpClient client = null;


    public static CloseableHttpClient getClient(String url){
        if (client == null){
            synchronized (lock){
                if (client == null){
                    ConnectionSocketFactory plainSocketFactory = PlainConnectionSocketFactory.getSocketFactory();
                    LayeredConnectionSocketFactory sslSocketFactory = SSLConnectionSocketFactory.getSocketFactory();
                    Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory> create().register("http", plainSocketFactory)
                            .register("https", sslSocketFactory).build();
                    manager = new PoolingHttpClientConnectionManager(registry);
                    manager.setMaxTotal(MAX_CONN); // 最大连接数
                    manager.setDefaultMaxPerRoute(Max_PRE_ROUTE); // 路由最大连接数

                    client = HttpClients.custom().setConnectionManager(manager).build();
                    scheduledPool = Executors.newScheduledThreadPool(1);
                    scheduledPool.scheduleAtFixedRate(new TimerTask() {
                        @Override
                        public void run() {
                            manager.closeExpiredConnections();
                            manager.closeIdleConnections(30, TimeUnit.SECONDS);
                            System.out.println("close expire tcp connect...");
                        }
                    },0,2,TimeUnit.SECONDS);
                }
            }
        }
        return client;
    }


    public static CloseableHttpClient getClient() {

        ConnectionSocketFactory plainSocketFactory = PlainConnectionSocketFactory.getSocketFactory();
        LayeredConnectionSocketFactory sslSocketFactory = SSLConnectionSocketFactory.getSocketFactory();
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create().register("http", plainSocketFactory)
                .register("https", sslSocketFactory).build();
        manager = new PoolingHttpClientConnectionManager(registry);
        manager.setMaxTotal(MAX_CONN); // 最大连接数
        manager.setDefaultMaxPerRoute(Max_PRE_ROUTE); // 路由最大连接数

        return HttpClients.custom().setConnectionManager(manager).build();

    }

    public static String doHttpPost(String url) {
        String result = "";
        CloseableHttpClient client = getClient(url);
        HttpPost post = new HttpPost(url);
        setRequestConfig(post);
        CloseableHttpResponse response = null;
        try {
            response  = client.execute(post);
            HttpEntity entity = response.getEntity();

            if (entity != null){
                result = EntityUtils.toString(entity);
                System.out.println("result is:" + result);
            }
        }catch (Exception e ){
            e.printStackTrace();
        }finally {
            try {
                if (response != null){
                    response.close();
                }
                client.close();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        return result;

    }

    private static void setRequestConfig(HttpRequestBase httpRequestBase){
        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(CONNECT_TIMEOUT)
                .setConnectTimeout(CONNECT_TIMEOUT)
                .setSocketTimeout(SOCKET_TIMEOUT).build();

        httpRequestBase.setConfig(requestConfig);
    }
}
