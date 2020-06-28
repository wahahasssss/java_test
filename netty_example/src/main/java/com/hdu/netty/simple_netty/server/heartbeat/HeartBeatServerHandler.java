package com.hdu.netty.simple_netty.server.heartbeat;

import com.hdu.netty.simple_netty.decodeer_encoder.SimpleEntity;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

import java.nio.charset.Charset;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2019/1/22
 * @Time 下午3:56
 */
public class HeartBeatServerHandler extends ChannelInboundHandlerAdapter{
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent){
            System.out.println("user event triggered happen....");
//            ctx.writeAndFlush(new SimpleEntity(
////                    String.valueOf(System.currentTimeMillis()),
////                    String.valueOf(System.currentTimeMillis())));
            ctx.writeAndFlush(Unpooled.copiedBuffer("HEARTBEAT",Charset.defaultCharset()));
        }else {
            super.userEventTriggered(ctx,evt);
        }
    }
}
