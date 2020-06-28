package com.hdu.netty.udp;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.util.CharsetUtil;

import java.util.Currency;
import java.util.List;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2019/1/14
 * @Time 下午7:59
 */
public class LogEventDecoder extends MessageToMessageDecoder<DatagramPacket>{
    @Override
    protected void decode(ChannelHandlerContext ctx, DatagramPacket msg, List<Object> out) throws Exception {
        ByteBuf buf = msg.content();
        int idx = buf.indexOf(0,buf.readableBytes(),LogEvent.SEPARATOR);
        String fileName = buf.slice(0,idx).toString(CharsetUtil.UTF_8);
        String logMsg = buf.slice(idx + 1,buf.readableBytes()-idx-1).toString(CharsetUtil.UTF_8);
        LogEvent event = new LogEvent(msg.sender(),fileName,logMsg,System.currentTimeMillis());
        out.add(event);
    }
}
