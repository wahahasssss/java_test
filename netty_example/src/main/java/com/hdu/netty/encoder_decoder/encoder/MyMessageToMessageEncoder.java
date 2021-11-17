package com.hdu.netty.encoder_decoder.encoder;

import com.hdu.netty.encoder_decoder.entity.TestEntity;
import com.hdu.netty.encoder_decoder.entity.TestEntity2;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2019/1/9
 * @Time 下午7:43
 */
public class MyMessageToMessageEncoder extends MessageToMessageEncoder<TestEntity2> {
    @Override
    protected void encode(ChannelHandlerContext ctx, TestEntity2 msg, List<Object> out) throws Exception {
        TestEntity entity = new TestEntity();
        entity.setAge(msg.getAge());
        String[] names = msg.getName().split(" ");
        entity.setFirstName(names[0]);
        entity.setLastName(names[1]);
        out.add(entity);
    }
}
