package com.hdu.netty.chapter0;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author: ssf
 * @Date: 2020/3/16 5:36 下午
 */
public class IOServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8000);

        while (true) {
            Socket socket = serverSocket.accept();
            InputStream is = socket.getInputStream();
            byte[] data = new byte[1024];
            int len = -1;
            while ((len = is.read(data)) != -1) {
                System.out.println(new String(data, 0, len));
            }
            socket.close();

        }

    }
}
