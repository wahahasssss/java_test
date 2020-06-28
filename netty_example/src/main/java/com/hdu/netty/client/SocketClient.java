package com.hdu.netty.client;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2019/1/3
 * @Time 下午3:53
 */
public class SocketClient {
    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("127.0.0.1",1234));
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(),true);

        while (true){
            out.println("Done");
            System.out.println(in.readLine());

    }
    }
}
