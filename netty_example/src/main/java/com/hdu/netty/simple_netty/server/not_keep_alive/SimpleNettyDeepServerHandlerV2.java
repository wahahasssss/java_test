package com.hdu.netty.simple_netty.server.not_keep_alive;

import com.hdu.netty.simple_netty.decodeer_encoder.SimpleEntity;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2019/1/22
 * @Time 下午2:14
 */
public class SimpleNettyDeepServerHandlerV2 extends ChannelInboundHandlerAdapter{
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(msg instanceof SimpleEntity){
            System.out.println("simple deep server v2 received msg is " + msg.toString());
            ctx.writeAndFlush(msg).addListener(ChannelFutureListener.CLOSE);
        }
        ReferenceCountUtil.release(msg);
    }
}
