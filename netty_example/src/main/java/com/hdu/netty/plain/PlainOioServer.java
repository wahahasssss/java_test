package com.hdu.netty.plain;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/3/27
 * @Time 上午11:37
 */
public class PlainOioServer {
    private static PlainOioServer PLAIN_OIO_SERVER;

    public PlainOioServer() {
    }

    public void server(int port) throws IOException {
        final ServerSocket socket = new ServerSocket(port);
        try {
            for (; ; ) {
                final Socket clientSocket = socket.accept();
                System.out.println("Accept connection from " + clientSocket);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        OutputStream outputStream;
                        try {
                            outputStream = clientSocket.getOutputStream();
                            outputStream.write("Hi ! \r\b".getBytes(Charset.forName("UTF-8")));
                            outputStream.flush();
                            clientSocket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            try {
                                clientSocket.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        PLAIN_OIO_SERVER = new PlainOioServer();
        PLAIN_OIO_SERVER.server(1234);
    }
}
