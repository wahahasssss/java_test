package com.hdu.client;

import com.hdu.server.service.MutService;
import com.hdu.server.service.MutServiceImpl;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/8/27
 * @Time 下午2:19
 */
public class ClientMain {
    public static final Logger LOGGER = LoggerFactory.getLogger(ClientMain.class);

    public static void main(String[] args) {
        TTransport tTransport = null;
        try {
            tTransport = new TSocket("127.0.0.1", 8989);
            TProtocol protocol = new TBinaryProtocol(tTransport);
            MutService.Client client = new MutService.Client(protocol);
            tTransport.open();
            String result = client.execute("thrift client send...");
            LOGGER.info("client get answer  {} from server", result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (tTransport != null && tTransport.isOpen()) {
                tTransport.close();
            }
        }
    }
}
