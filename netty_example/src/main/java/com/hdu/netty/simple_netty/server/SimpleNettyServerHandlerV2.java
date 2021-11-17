package com.hdu.netty.simple_netty.server;

import com.hdu.netty.simple_netty.decodeer_encoder.SimpleEntity;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2019/1/22
 * @Time 下午2:11
 */
public class SimpleNettyServerHandlerV2 extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof SimpleEntity) {
            System.out.println("v2 simple receive msg is " + msg.toString());
            ctx.fireChannelRead(msg);
        }
    }
}
