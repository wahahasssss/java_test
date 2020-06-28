package com.hdu.netty.pre_channelhandler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * DESCRIPTION:聚合HTTP 请求和响应消息
 *
 * @author shushoufu
 * @Date 2019/1/10
 * @Time 上午11:23
 */
public class HttpAggregatorInitializer extends ChannelInitializer<Channel>{
    private final Boolean isClient;

    public HttpAggregatorInitializer(Boolean isClient) {
        this.isClient = isClient;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
        if (isClient){
            ch.pipeline().addLast("codec",new HttpClientCodec());
        }else {
            ch.pipeline().addLast("codec",new HttpServerCodec());
        }
        ch.pipeline().addLast("aggregator",new HttpObjectAggregator(512*1024));//最大消息大小限制为512KB
    }
}
