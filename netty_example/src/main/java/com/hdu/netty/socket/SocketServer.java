package com.hdu.netty.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/3/26
 * @Time 上午10:53
 */
public class SocketServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234);
        Socket socket = serverSocket.accept();
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        try {
            String request, response;
            while ((request = in.readLine()) != null) {
                if ("Done".equals(request)) {
                    socket.close();
                }
                response = String.format("the response is %s", request);
                System.out.println(response);
                out.println(response);

                Socket socket1 = new Socket();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
