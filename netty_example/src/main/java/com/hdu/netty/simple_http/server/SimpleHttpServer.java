package com.hdu.netty.simple_http.server;

import com.hdu.netty.simple_http.handler.SimpleHttpRequestHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2019/1/23
 * @Time 下午2:38
 */
public class SimpleHttpServer {
    public ServerBootstrap buildServerBootstrap() {
        EventLoopGroup bossGroup = new NioEventLoopGroup(100);
        EventLoopGroup workerGroup = new NioEventLoopGroup(1000);
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_REUSEADDR, true)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new HttpServerCodec());
                        pipeline.addLast(new HttpObjectAggregator(8 * 1024 * 1024));
                        pipeline.addLast(new SimpleHttpRequestHandler());
                    }
                });
        return bootstrap;
    }


    public Channel startHttpServer(ServerBootstrap bootstrap) {
        ChannelFuture future = bootstrap.bind(8080).syncUninterruptibly();
        Channel channel = future.channel();
        return channel;
    }


    public static void main(String[] args) {
        SimpleHttpServer httpServer = new SimpleHttpServer();

        ServerBootstrap bootstrap = httpServer.buildServerBootstrap();
        Channel channel = httpServer.startHttpServer(bootstrap);
        channel.closeFuture();

    }
}
