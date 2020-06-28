package com.hdu.consul.register;

import com.google.common.net.HostAndPort;
import com.orbitz.consul.AgentClient;
import com.orbitz.consul.Consul;
import com.orbitz.consul.NotRegisteredException;
import com.orbitz.consul.model.agent.ImmutableRegCheck;
import com.orbitz.consul.model.agent.ImmutableRegistration;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/3/14
 * @Time 下午7:02
 */
public class ServiceRegister {
    public static final String CONSUL_IP = "10.69.216.88";
    public static final Integer CONSUL_PORT = 8080;
    private static final AgentClient agentClient;
    static {
//        agentClient = Consul.builder().withHostAndPort(HostAndPort.fromParts(Constant.CONSUL_IP,Constant.CONSUL_PORT)).build().agentClient();
        agentClient = Consul.builder().withHostAndPort(HostAndPort.fromParts(CONSUL_IP,CONSUL_PORT)).build().agentClient();
    }
    public static void registerService(String serviceName,Integer serviceId) throws NotRegisteredException {
        //agentClient.register(8080,3L,serviceName,serviceId.toString());
//        agentClient.register(8080,HostAndPort.fromParts(Constant.CONSUL_IP,Constant.CONSUL_PORT),3,serviceName,serviceId.toString(),"one","two");
//        agentClient.pass(serviceId.toString());
        ImmutableRegCheck check = ImmutableRegCheck.builder().tcp("47.94.245.160:8081").interval("5s").build();
        ImmutableRegistration.Builder builder = ImmutableRegistration.builder();
        builder.id("dispatcher&ssf.local").name("dispatcher&ssf.local").address("172.19.126.254").port(8552).addChecks(check);
        agentClient.register(builder.build());
    }


    public static void unRegisterService(){
        agentClient.deregister("dispatcher&rz-waimai-d-clouddispatcher-staging03.rz.sankuai.com");
    }


    public static void main(String[] args){
        unRegisterService();
    }
}
