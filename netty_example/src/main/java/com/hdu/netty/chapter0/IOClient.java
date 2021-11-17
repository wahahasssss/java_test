package com.hdu.netty.chapter0;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.text.DateFormat;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;

/**
 * @Author: ssf
 * @Date: 2020/3/16 5:43 下午
 */
public class IOClient {
    public static void main(String[] args) throws IOException, InterruptedException {

        while (true) {
            Socket socket = new Socket("127.0.0.1", 8000);
            OutputStream os = socket.getOutputStream();
            os.write((new Date().toString() + ":hello word").getBytes());
            os.flush();
            socket.close();
            Thread.sleep(1000);
        }
    }
}
