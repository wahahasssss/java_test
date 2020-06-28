package com.hdu;

import javax.management.*;
import java.lang.management.ManagementFactory;
import java.nio.ByteBuffer;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/12/27
 * @Time 下午4:13
 */
public class HelloAgent {
    public static void main(String[] args) throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException, InterruptedException {
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        ObjectName helloName = new ObjectName("jmxBean:name=hello");
        ByteBuffer buffer = ByteBuffer.allocateDirect(2000*1024*1024);
        while (true){

            buffer.put("hahfawfaweff".getBytes());

        }
    }
}
