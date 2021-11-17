package com.hdu.netty.simple_netty.client;

import com.hdu.netty.simple_netty.decodeer_encoder.SimpleEntity;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;

import java.nio.charset.Charset;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2019/1/21
 * @Time 下午7:38
 */
public class SimpleNettyClientHandler extends SimpleChannelInboundHandler<SimpleEntity> {
    private static volatile AtomicInteger ATTEMPS = new AtomicInteger(0);
    private Bootstrap bootstrap;

    public SimpleNettyClientHandler(Bootstrap bootstrap) {
        this.bootstrap = bootstrap;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel active");
        SimpleEntity entity = new SimpleEntity();
        entity.setDate("one");
        entity.setTime("two");
        ATTEMPS.set(0);
        ctx.writeAndFlush(entity);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SimpleEntity msg) throws Exception {
        System.out.println("client receive msg" + msg.toString());
        msg.setTime("four");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel in active");
        if (ATTEMPS.get() < 12) {
            Thread.sleep(1000);
            ChannelFuture future = bootstrap.connect("127.0.0.1", 6787);
            SimpleNettyClient.channel = future.channel();
            future.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (future.isSuccess()) {
                        System.out.println("reconnect success");
                    } else {
                        System.out.println("reconnect failed");
                        future.channel().pipeline().fireChannelInactive();
                    }
                }
            });
        }

    }
}
