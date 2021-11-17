package com.hdu.netty.websocket.chatroom;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.ssl.util.SelfSignedCertificate;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.util.concurrent.ImmediateEventExecutor;

import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLException;
import java.security.cert.CertificateException;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2019/1/11
 * @Time 上午11:37
 */
public class SecureChatServer {
    private final ChannelGroup channelGroup = new DefaultChannelGroup(ImmediateEventExecutor.INSTANCE);
    private Channel channel;
    private SslContext sslContext;
    private EventLoopGroup bossGroup, workerGroup;

    public SecureChatServer(SslContext sslContext) {
        this.sslContext = sslContext;
    }

    public ChannelFuture startSecureServer() {
        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap
                .group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        SSLEngine sslEngine = sslContext.newEngine(ch.alloc());
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addFirst(new SslHandler(sslEngine));
                        pipeline.addLast(new HttpServerCodec());
                        pipeline.addLast(new ChunkedWriteHandler());
                        pipeline.addLast(new HttpObjectAggregator(64 * 1024));
                        pipeline.addLast(new HttpRequestHandler("/ws"));
                        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
                        pipeline.addLast(new TextWebSocketFrameHandler(channelGroup));
                    }
                });
        ChannelFuture future = serverBootstrap.bind(9999);
        future.syncUninterruptibly();
        channel = future.channel();
        return future;

    }

    public void destroy() {
        if (channel != null) {
            channel.close();
        }
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
        channelGroup.close();

    }

    public static void main(String[] args) throws CertificateException, SSLException {
        System.out.println("begin secure chat server");
        SelfSignedCertificate certificate = new SelfSignedCertificate();
        SslContext sslContext = SslContextBuilder.forServer(certificate.certificate(), certificate.privateKey()).build();

        SecureChatServer chatServer = new SecureChatServer(sslContext);
        ChannelFuture future = chatServer.startSecureServer();


        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                chatServer.destroy();
            }
        }));

        future.channel().closeFuture().syncUninterruptibly();
    }
}
