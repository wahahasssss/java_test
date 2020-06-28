package com.hdu.netty.encoder_decoder.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.TooLongFrameException;

import java.util.List;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2019/1/9
 * @Time 下午7:27
 */
public class SafeByteToMessageDecoder extends ByteToMessageDecoder{
    private static final int MAX_FRAME_SIZE = 2014;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        int readable = in.readableBytes();
        if (readable>MAX_FRAME_SIZE){
            in.skipBytes(readable);
            throw new TooLongFrameException("FRAME IS TOO LONG");
        }
    }
}
