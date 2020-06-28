package com.hdu.netty.simple_netty.server;

import com.hdu.netty.simple_netty.decodeer_encoder.SimpleEntity;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2019/1/21
 * @Time 下午7:07
 */
public class SimpleNettyServerHandler extends SimpleChannelInboundHandler<SimpleEntity>{

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SimpleEntity msg) throws Exception {
        System.out.println("update simple entity");
        ctx.fireChannelRead(msg);
    }
}
