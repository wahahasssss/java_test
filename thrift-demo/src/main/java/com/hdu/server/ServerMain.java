package com.hdu.server;

import com.hdu.server.service.MutService;
import com.hdu.server.service.MutServiceImpl;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/8/27
 * @Time 下午2:10
 */
public class ServerMain {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServerMain.class);

    public static void main(String[] args) {
        try {
            TServerSocket serverTransport = new TServerSocket(8989);
            TServer.Args args1 = new TServer.Args(serverTransport);
            TProcessor processor = new MutService.Processor(new MutServiceImpl());
            args1.processor(processor);
            TServer server = new TSimpleServer(args1);
            server.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
