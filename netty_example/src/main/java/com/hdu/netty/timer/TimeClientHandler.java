package com.hdu.netty.timer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.Date;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/3/24
 * @Time 下午3:37
 */
public class TimeClientHandler extends ChannelInboundHandlerAdapter {

    private ByteBuf b = Unpooled.buffer();

    /**
     * Do nothing by default, sub-classes may override this method.
     *
     * @param ctx
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        b.alloc().buffer(4);
    }

    /**
     * Do nothing by default, sub-classes may override this method.
     *
     * @param ctx
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        if (b != null) {
            b.release();
            b = null;
        }

//        ctx.pipeline().get()
    }

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        System.out.println(msg.toString());
        long timestamp = buf.readUnsignedInt();
        long currentTimeMillis = (timestamp - 2208988800L) * 1000L;
        Date date = new Date(currentTimeMillis);
        System.out.println(date);
        if (buf.refCnt() > 0) {
            buf.release();
        }
        ctx.channel().writeAndFlush(Unpooled.copiedBuffer(date.toString(), Charset.defaultCharset()));

    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
