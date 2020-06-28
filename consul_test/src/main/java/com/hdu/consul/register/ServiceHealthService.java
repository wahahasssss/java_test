package com.hdu.consul.register;

import com.alibaba.fastjson.JSON;
import com.google.common.net.HostAndPort;
import com.orbitz.consul.AgentClient;
import com.orbitz.consul.Consul;
import com.orbitz.consul.HealthClient;
import com.orbitz.consul.NotRegisteredException;
import com.orbitz.consul.cache.ConsulCache;
import com.orbitz.consul.cache.ServiceHealthCache;
import com.orbitz.consul.cache.ServiceHealthKey;
import com.orbitz.consul.model.agent.*;
import com.orbitz.consul.model.health.ServiceHealth;
import com.orbitz.consul.option.QueryOptions;

import java.util.*;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/3/14
 * @Time 下午7:53
 */
public class ServiceHealthService {
    public static final String CONSUL_IP = "127.0.0.1";
    public static final Integer CONSUL_PORT = 8414;
    private final static HealthClient HEALTH_CLIENT;
    private static final AgentClient agentClient;
    static {
//        agentClient = Consul.builder().withHostAndPort(HostAndPort.fromParts(Constant.CONSUL_IP,Constant.CONSUL_PORT)).build().agentClient();
        agentClient = Consul.builder().withHostAndPort(HostAndPort.fromParts(CONSUL_IP,CONSUL_PORT)).build().agentClient();
        HEALTH_CLIENT = Consul.builder().withHostAndPort(HostAndPort.fromParts(CONSUL_IP, CONSUL_PORT)).build().healthClient();
    }



    public static void registerServiceWithCheck(String serviceName,String serviceId,String serviceIp,Integer port,Long keepAliveTime) throws NotRegisteredException {
        ImmutableRegCheck check = ImmutableRegCheck.builder().tcp(String.format("%s:%s",serviceIp,port)).interval(keepAliveTime.toString() + "s").build();
        ImmutableRegistration.Builder builder = ImmutableRegistration.builder();
        builder.id(serviceId).name(serviceName).address(serviceIp).port(port).addChecks(check);
        builder.putMeta("one","1");
        agentClient.register(builder.build());
    }


    public static void unRegisterService(String serviceId){
        agentClient.deregister(serviceId);
    }

    public static void registerCheck(String checkId,String checkName,String tcp){
        Check check = ImmutableCheck.builder().tcp(tcp)
                .name(checkName).id(checkId).interval("5s").build();
        agentClient.registerCheck(check);
    }

    public static void unregisterCheck(String checkId){
        agentClient.deregisterCheck(checkId);
    }

    public static List<ServiceHealth> findHealthService(String serviceName){
        return HEALTH_CLIENT.getHealthyServiceInstances(serviceName).getResponse();
    }

    /**
     * 订阅指定服务状态
     * @param serviceName
     */
    public static ServiceHealthCache subscribeHealthService(String serviceName){
        ServiceHealthCache healthCache = ServiceHealthCache.newCache(HEALTH_CLIENT, serviceName);
        healthCache.addListener(new ConsulCache.Listener<ServiceHealthKey, ServiceHealth>() {
            @Override
            public void notify(Map<ServiceHealthKey, ServiceHealth> map) {
                Iterator iterator = map.entrySet().iterator();
                while (iterator.hasNext()){
                    Map.Entry entry = (Map.Entry) iterator.next();
                    ServiceHealthKey serviceHealthKey = (ServiceHealthKey)entry.getKey();
                    ServiceHealth health = (ServiceHealth)entry.getValue();
                    System.out.println("update done: serviceKey is " + JSON.toJSONString(serviceHealthKey)  +
                            ", serviceHealth is " + JSON.toJSONString(health));
                }
            }
        });
        return healthCache;
    }

    public static void main(String[] args) throws NotRegisteredException, InterruptedException {
//        /**
//         * 注册服务（且注册了检查机制）
//         */
//        registerServiceWithCheck("blog","blog_01","47.94.245.160",9998,5L);
//
//        /**
//         * 注销服务
//         */
//        unRegisterService("blog_01");

//        registerCheck("blog_check_01","blog_check","47.94.245.160:9998");
//
//        unregisterCheck("blog_check_01");

        ServiceHealthCache healthCache  = subscribeHealthService("blog");
        healthCache.start();
        while (true){
            for (ServiceHealth serviceHealth : ServiceHealthService.findHealthService("blog")) {
//                System.out.println(serviceHealth.toString());
                //do something
            }
            Thread.sleep(1000);
        }
    }
}
