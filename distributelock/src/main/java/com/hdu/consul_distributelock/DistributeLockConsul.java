package com.hdu.consul_distributelock;

import com.alibaba.fastjson.JSON;
import com.orbitz.consul.Consul;
import com.orbitz.consul.KeyValueClient;
import com.orbitz.consul.SessionClient;
import com.orbitz.consul.model.session.ImmutableSession;
import com.orbitz.consul.model.session.Session;
import com.orbitz.consul.model.session.SessionCreatedResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/8/6
 * @Time 下午5:11
 */
public class DistributeLockConsul {


    private static final String prefix = "lock/";

    private Consul consul;

    private String sessionName;
    private String sessionId;
    private String lockKey;

    public DistributeLockConsul(Consul consul, String sessionName, String lockKey) {
        this.consul = consul;
        this.sessionName = sessionName;
        this.lockKey = lockKey;
    }


    public Boolean lock(boolean block) throws InterruptedException {
        KeyValueClient client = consul.keyValueClient();
        this.sessionId = createSession(sessionName);
        System.out.println(String.format("Thread %s SessionId is %s",Thread.currentThread().getName(),sessionId));
        String uuid = UUID.randomUUID().toString();
        while (true){
            if (client.acquireLock(String.format("%s%s",prefix,lockKey),uuid,this.sessionId)){
                return true;
            }else {
                Thread.sleep(1000);
            }

        }
    }

    public Boolean releaseLock(){
        KeyValueClient client = consul.keyValueClient();
        Boolean result = client.releaseLock(String.format("%s%s",prefix,lockKey),this.sessionId);
        destroySession();
        return result;
    }


    private void destroySession(){
//        CloseableHttpClient client = HttpClients.createDefault();
//        String url = String.format("http://127.0.0.1:8414/session/destory/%s",this.sessionId);
//        HttpPut put = new HttpPut(url);
//        try {
//            CloseableHttpResponse response = client.execute(put);
//            String result = EntityUtils.toString(response.getEntity());
//        }catch (Exception e){
//            e.printStackTrace();
//        }
        SessionClient client = consul.sessionClient();
        client.destroySession(this.sessionId);
    }

    private String createSession(String sessionName){

        SessionClient sessionClient = consul.sessionClient();
        Session session = ImmutableSession.builder()
                .lockDelay("15s")
                .name(sessionName)
                .node("shushoufudeMacBook-Pro-2.local")
                .behavior("release")
                .ttl("30s")
                .build();
        SessionCreatedResponse response = sessionClient.createSession(session);
        return response.getId();
    }

}
