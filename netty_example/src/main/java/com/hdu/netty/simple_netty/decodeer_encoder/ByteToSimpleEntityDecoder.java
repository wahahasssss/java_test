package com.hdu.netty.simple_netty.decodeer_encoder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.nio.charset.Charset;
import java.util.List;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2019/1/21
 * @Time 下午6:53
 */
public class ByteToSimpleEntityDecoder extends ByteToMessageDecoder {
    private static final SerializeUtil SERIALIZE_UTIL;

    static {
        SERIALIZE_UTIL = new SerializeUtil();
    }
//    /**
//     * 自定义的序列化
//     * @param ctx
//     * @param in
//     * @param out
//     * @throws Exception
//     */
//    @Override
//    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
//        ByteBuf tmp = in.readBytes(in.readableBytes());
//        int idx = tmp.indexOf(0,tmp.readableBytes(),(byte)'|');
//        SimpleEntity simpleEntity = new SimpleEntity();
//        simpleEntity.setDate(tmp.slice(0,idx-1).toString(Charset.defaultCharset()));
//        simpleEntity.setTime(tmp.slice(idx+1,tmp.readableBytes() - idx -1).toString(Charset.defaultCharset()));
//        tmp.release();
//        out.add(simpleEntity);
//    }

    /**
     * kryo序列化
     *
     * @param ctx
     * @param in
     * @param out
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        byte[] bytes = new byte[in.readableBytes()];
        in.readBytes(bytes, 0, in.readableBytes());
        Object instance = SERIALIZE_UTIL.deserialization(bytes, SimpleEntity.class);
        if (instance != null) {
            out.add(SERIALIZE_UTIL.deserialization(bytes, SimpleEntity.class));
        }
    }
}
