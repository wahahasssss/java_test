package com.hdu.netty.encoder_decoder.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2019/1/8
 * @Time 下午8:48
 */
public class ToIntegerDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        if (byteBuf.readableBytes() >= 4) {
            list.add(byteBuf.readInt());
        }
    }
}
