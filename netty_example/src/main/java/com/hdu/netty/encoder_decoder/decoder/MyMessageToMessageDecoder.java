package com.hdu.netty.encoder_decoder.decoder;

import com.hdu.netty.encoder_decoder.entity.TestEntity;
import com.hdu.netty.encoder_decoder.entity.TestEntity2;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2019/1/9
 * @Time 下午7:19
 */
public class MyMessageToMessageDecoder extends MessageToMessageDecoder<TestEntity>{
    @Override
    protected void decode(ChannelHandlerContext ctx, TestEntity msg, List<Object> out) throws Exception {
        TestEntity2 entity2 = new TestEntity2();
        entity2.setAge(msg.getAge());
        entity2.setName(String.format("%s %s",msg.getFirstName(),msg.getLastName()));
    }
}
