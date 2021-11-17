package com.hdu.netty.simple_netty.decodeer_encoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2019/1/21
 * @Time 下午5:52
 */
public class SimpleEntityToByteEncoder extends MessageToByteEncoder<SimpleEntity> {
    private static final SerializeUtil SERIALIZE_UTIL;

    static {
        SERIALIZE_UTIL = new SerializeUtil();
    }
//    /**
//     *
//     * @param ctx
//     * @param msg
//     * @param out
//     * @throws Exception
//     */
//    @Override
//    protected void encode(ChannelHandlerContext ctx, SimpleEntity msg, ByteBuf out) throws Exception {
//
//
//        out.writeBytes(msg.getDate().getBytes());
//        out.writeChar('|');
//        out.writeBytes(msg.getTime().getBytes());
//
//    }


    /**
     * 通过 kyro压缩
     *
     * @param ctx
     * @param msg
     * @param out
     * @throws Exception
     */
    @Override
    protected void encode(ChannelHandlerContext ctx, SimpleEntity msg, ByteBuf out) throws Exception {
        byte[] bytes = SERIALIZE_UTIL.serialization(msg);
        out.writeBytes(bytes);
    }
}
