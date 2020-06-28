package com.hdu.netty.simple_netty.server.keep_alive;

import com.hdu.netty.simple_netty.decodeer_encoder.SimpleEntity;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2019/1/22
 * @Time 下午2:05
 */
public class SimpleNettyDeepServerHandler extends SimpleChannelInboundHandler<SimpleEntity>{
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SimpleEntity msg) throws Exception {
        System.out.println("simple netty deep server handler" + msg.toString());
        ctx.writeAndFlush(msg).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
    }
}
