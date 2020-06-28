package com.hdu;

import com.hdu.handler.TestHandler;
import io.undertow.Undertow;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/4/17
 * @Time 下午2:54
 */
public class Main {
    public static void main(String[] args){
        TestHandler handler = new TestHandler();
        Undertow server = Undertow.builder()
                .addHttpListener(9999,"127.0.0.1")
                .setHandler(handler)
                .build();
        server.start();
    }
}
