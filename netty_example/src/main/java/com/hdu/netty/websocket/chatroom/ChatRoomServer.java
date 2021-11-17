package com.hdu.netty.websocket.chatroom;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.util.concurrent.ImmediateEventExecutor;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2019/1/10
 * @Time 下午7:18
 */
public class ChatRoomServer {

    protected final ChannelGroup channelGroup = new DefaultChannelGroup(ImmediateEventExecutor.INSTANCE);
    protected Channel channel;

    public ChannelFuture startChatRoomServer() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new HttpServerCodec());
                        pipeline.addLast(new ChunkedWriteHandler());
                        pipeline.addLast(new HttpObjectAggregator(64 * 1024));
                        pipeline.addLast(new HttpRequestHandler("/ws"));
                        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
                        pipeline.addLast(new TextWebSocketFrameHandler(channelGroup));
                    }
                });
        ChannelFuture future = bootstrap.bind(9999);
        future.syncUninterruptibly();
        channel = future.channel();
        return future;
    }


    public void destroy() {
        if (channel != null) {
            channel.close();
        }
        channelGroup.close();

    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("begin chatroom server");
        ChatRoomServer chatRoomServer = new ChatRoomServer();
        ChannelFuture future = chatRoomServer.startChatRoomServer();
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                chatRoomServer.destroy();
            }
        }));
        future.channel().closeFuture().syncUninterruptibly();
    }
}
