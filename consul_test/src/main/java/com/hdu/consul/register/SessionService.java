package com.hdu.consul.register;

import com.google.common.net.HostAndPort;
import com.orbitz.consul.Consul;
import com.orbitz.consul.KeyValueClient;
import com.orbitz.consul.SessionClient;
import com.orbitz.consul.model.kv.Value;
import com.orbitz.consul.model.session.ImmutableSession;
import com.orbitz.consul.model.session.Session;
import com.orbitz.consul.model.session.SessionCreatedResponse;

import java.util.Optional;
import java.util.UUID;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/8/7
 * @Time 下午4:50
 */
public class SessionService {
    public static final String CONSUL_IP = "127.0.0.1";
    public static final Integer CONSUL_PORT = 8414;
    private static final SessionClient SESSION_CLIENT;
    private static final KeyValueClient KEY_VALUE_CLIENT;
    private static Integer COUNT = 100;
    static {
        SESSION_CLIENT = Consul.builder().withHostAndPort(HostAndPort.fromParts(CONSUL_IP,CONSUL_PORT)).build().sessionClient();
        KEY_VALUE_CLIENT = Consul.builder().withHostAndPort(HostAndPort.fromParts(CONSUL_IP,CONSUL_PORT)).build().keyValueClient();
    }

    /**
     * 创建session
     * @param sessionName
     * @return
     */
    public static String createSession(String sessionName){
        Session session = ImmutableSession.builder().name(sessionName)
                .build();
       return SESSION_CLIENT.createSession(session).getId();
    }

    public static void destroySession(String sessionId){
        SESSION_CLIENT.destroySession(sessionId);
    }

    /**
     * 获取锁
     * @param sessionName
     */
    public static void acquireLock(String sessionName,String key) throws InterruptedException {
        Session session = ImmutableSession.builder().name(sessionName).build();
        SessionCreatedResponse response = SESSION_CLIENT.createSession(session);
        String name = session.getName().get();
        String sessionId = response.getId();
        if (KEY_VALUE_CLIENT.acquireLock(key, name, sessionId)){
            COUNT--;
            System.out.println("current count is :" + COUNT);
            System.out.println("sessionIs is :" + sessionId);
            Thread.sleep(1000);
            KEY_VALUE_CLIENT.releaseLock(key, sessionId);
            SESSION_CLIENT.destroySession(sessionId);
        }else {
            System.out.println("acquireLock Error");
        }
    }

    /**
     *
     * @param key
     * @param sessionId
     */
    public static void releaseLock(String key,String sessionId){
        KEY_VALUE_CLIENT.releaseLock(key, sessionId);
        KEY_VALUE_CLIENT.deleteKey(key);
         SESSION_CLIENT.destroySession(sessionId);
    }
    public static void main(String[] args) throws InterruptedException {
//        String sessionId = SessionService.createSession("session_" + UUID.randomUUID().toString());
//        destroySession("acc7e759-57eb-2f65-b6b0-f6d145fea5d2");
//        acquireLock("session_" + UUID.randomUUID().toString(), "/lock/session");
        releaseLock("/lock/session", "953ec304-99b7-f932-5621-946cb07ee75f");
    }

}
