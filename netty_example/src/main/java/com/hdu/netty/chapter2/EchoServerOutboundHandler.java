package com.hdu.netty.chapter2;

import io.netty.channel.*;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/8/20
 * @Time 上午10:33
 */
public class EchoServerOutboundHandler extends ChannelOutboundHandlerAdapter {
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        promise.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                if (!channelFuture.isSuccess()){
                    System.out.println("handle failed ...");
                }else {
                    System.out.println("handle success...");
                }
            }
        });
        super.write(ctx, msg, promise);
    }
}
