package com.hdu.netty.discard;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/8/10
 * @Time 下午5:10
 */
public class DiscardOutboundHandler extends ChannelOutboundHandlerAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(DiscardOutboundHandler.class);

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        LOGGER.info(String.format("write message : %s", buf.toString(Charset.defaultCharset())));
        ReferenceCountUtil.release(msg);
        promise.setSuccess();
    }
}
