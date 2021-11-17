package com.hdu.netty.chapter2;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/8/9
 * @Time 下午5:10
 */
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
    private static final Logger LOGGER = LoggerFactory.getLogger(EchoClientHandler.class);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        LOGGER.info(String.format("client receive connect %s", ctx.channel().remoteAddress()));
        ctx.writeAndFlush(Unpooled.copiedBuffer("Client received connect " + ctx.channel().remoteAddress().toString(), Charset.defaultCharset()));
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        LOGGER.info(String.format("Client receive msg %s from %s", msg.toString(Charset.defaultCharset()), ctx.channel().remoteAddress()));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        LOGGER.error(cause.getMessage());
    }

}
