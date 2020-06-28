package com.hdu.netty.pre_channelhandler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.http.HttpResponseDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

/**
 * DESCRIPTION:完成对消息的编解码操作
 *
 * @author shushoufu
 * @Date 2019/1/10
 * @Time 上午11:11
 */

public class HttpPipelineInitializer extends ChannelInitializer<Channel>{
    private final Boolean IS_CLIENT;

    public HttpPipelineInitializer(Boolean isClient) {
        IS_CLIENT = isClient;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
        if (IS_CLIENT){
            ch.pipeline().addLast("decoder",new HttpResponseDecoder());
            ch.pipeline().addLast("encoder",new HttpRequestEncoder());
        }else {
            ch.pipeline().addLast("decoder",new HttpRequestDecoder());
            ch.pipeline().addLast("encoder",new HttpResponseEncoder());
        }
    }
}
