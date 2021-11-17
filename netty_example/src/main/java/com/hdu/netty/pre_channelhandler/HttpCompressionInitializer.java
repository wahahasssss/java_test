package com.hdu.netty.pre_channelhandler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpContentCompressor;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2019/1/10
 * @Time 上午11:32
 */
public class HttpCompressionInitializer extends ChannelInitializer<Channel> {
    private final Boolean isClient;

    public HttpCompressionInitializer(Boolean isClient) {
        this.isClient = isClient;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
        if (isClient) {
            ch.pipeline().addLast("codec", new HttpClientCodec());
            ch.pipeline().addLast("decompressor", new HttpContentCompressor());//如果是客户端需要处理来自服务器的压缩内容
        } else {
            ch.pipeline().addLast("codec", new HttpServerCodec());
            ch.pipeline().addLast("compressor", new HttpContentCompressor());//如果是服务器，则添加HttpContentCompressor来压缩数据
        }
    }
}
