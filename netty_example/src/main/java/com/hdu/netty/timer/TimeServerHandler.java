package com.hdu.netty.timer;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/3/24
 * @Time 下午3:13
 */
public class TimeServerHandler extends ChannelInboundHandlerAdapter{

    @Override
    public void channelActive(final ChannelHandlerContext ctx) throws Exception {
        System.out.println(String.format("channel is active...."));
        final ByteBuf time = ctx.alloc().buffer(4);
        time.writeInt((int)(System.currentTimeMillis()/1000L-2208988800L));
        ctx.writeAndFlush(time);
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf)msg;
        String receiveString =  buf.toString(Charset.defaultCharset());

        System.out.println("received msg is " + receiveString);
        buf.release();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("========>>>>>>..");
    }
//    @Override
//    public void channelActive(final ChannelHandlerContext ctx) throws Exception {
//        ChannelFuture f = ctx.writeAndFlush(new UnixTime());
//        f.addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
//    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
