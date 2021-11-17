package com.hdu.consul.register;

import com.google.common.net.HostAndPort;
import com.orbitz.consul.Consul;
import com.orbitz.consul.StatusClient;
import jdk.internal.org.objectweb.asm.tree.FieldInsnNode;

import java.util.List;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/3/14
 * @Time 下午8:30
 */
public class StatusClientService {
    private final static StatusClient STATUS_CLIENT;

    static {
        STATUS_CLIENT = Consul.builder().withHostAndPort(HostAndPort.fromParts(Constant.CONSUL_IP, Constant.CONSUL_PORT)).build().statusClient();
    }

    public static List<String> findRaftPeers() {
        return STATUS_CLIENT.getPeers();
    }
}
