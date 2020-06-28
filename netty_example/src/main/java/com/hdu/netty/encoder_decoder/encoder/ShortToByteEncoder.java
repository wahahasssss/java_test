package com.hdu.netty.encoder_decoder.encoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2019/1/9
 * @Time 下午7:35
 */
public class ShortToByteEncoder extends MessageToByteEncoder<Short>{
    @Override
    protected void encode(ChannelHandlerContext ctx, Short msg, ByteBuf out) throws Exception {
        out.writeShort(msg);
    }
}
