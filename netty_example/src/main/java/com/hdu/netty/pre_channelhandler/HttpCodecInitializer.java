package com.hdu.netty.pre_channelhandler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslHandler;

import javax.net.ssl.SSLEngine;

/**
 * DESCRIPTION:使用HTTPs
 *
 * @author shushoufu
 * @Date 2019/1/10
 * @Time 上午11:42
 */
public class HttpCodecInitializer extends ChannelInitializer<Channel> {
    private final Boolean isClient;
    private final SslContext sslContext;

    public HttpCodecInitializer(Boolean isClient, SslContext sslContext) {
        this.isClient = isClient;
        this.sslContext = sslContext;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
        SSLEngine sslEngine = sslContext.newEngine(ch.alloc());
        ch.pipeline().addFirst("ssl", new SslHandler(sslEngine));
        if (isClient) {
            ch.pipeline().addLast("codec", new HttpClientCodec());
        } else {
            ch.pipeline().addLast("codec", new HttpServerCodec());
        }
    }
}
