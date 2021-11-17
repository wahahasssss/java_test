package com.hdu.consul;

import com.alibaba.fastjson.JSONObject;
import com.google.common.net.HostAndPort;
import com.hdu.consul.register.KeyValueService;
import com.hdu.consul.register.ServiceHealthService;
import com.hdu.consul.register.ServiceRegister;
import com.hdu.consul.register.StatusClientService;
import com.orbitz.consul.AgentClient;
import com.orbitz.consul.Consul;
import com.orbitz.consul.NotRegisteredException;
import com.orbitz.consul.StatusClient;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/3/14
 * @Time 下午7:01
 */
public class Main {
    public static final String CONSUL_IP = "10.69.216.88";
    public static final Integer CONSUL_PORT = 8080;

    public static void main(String[] args) {
//        try {
//            Boolean result =  KeyValueService.exits("flux/fluxapplication/test_parser");
//            System.out.println(result);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
        long begin = System.currentTimeMillis();
        String str = "";
        String jsonStr = JSONObject.toJSONString(str);
        long end = System.currentTimeMillis();
        System.out.println(end - begin);


        Byte b = 1;
        if (null == b) {

        }
//        ServiceRegister.unRegisterService();
//        ServiceRegister.unRegisterService();
//        ServiceHealthService.findHealthService().parallelStream().forEach(h->{
//            System.out.println(h.getService().toString());
//        });

//        String kv_path = "/1/2/3";
//        if (KeyValueService.exits(kv_path)){
//            System.out.println(KeyValueService.getKv(kv_path));
//        }else {
//            KeyValueService.putKv(kv_path,String.format("now is %d",System.currentTimeMillis()));
//            System.out.println(KeyValueService.getKv(kv_path));
//        }
//        KeyValueService.delKv(kv_path);
//        System.out.println("______________________________________");
//        StatusClientService.findRaftPeers().parallelStream().forEach(p->{
//            System.out.println(p);
//        });

        AgentClient agentClient = Consul.builder().withHostAndPort(HostAndPort.fromParts(CONSUL_IP, CONSUL_PORT)).build().agentClient();
        agentClient.deregisterCheck("dispatcher&rz-waimai-d-clouddispatcher-staging03.rz.sankuai.com");
    }
}
