package com.hdu.netty.simple_netty.server;

import com.hdu.netty.simple_netty.decodeer_encoder.ByteToSimpleEntityDecoder;
import com.hdu.netty.simple_netty.decodeer_encoder.SimpleEntityToByteEncoder;
import com.hdu.netty.simple_netty.server.heartbeat.HeartBeatServerHandler;
import com.hdu.netty.simple_netty.server.keep_alive.SimpleNettyDeepServerHandlerV2;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2019/1/21
 * @Time 下午7:07
 */
public class SimpleNettyServer {
    public ServerBootstrap buildBootstrap() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new IdleStateHandler(10, 10, 60));
                        pipeline.addLast(new HeartBeatServerHandler());
                        pipeline.addLast(new SimpleEntityToByteEncoder());
                        pipeline.addLast(new ByteToSimpleEntityDecoder());
                        pipeline.addLast(new SimpleNettyServerHandlerV2());
                        pipeline.addLast(new SimpleNettyDeepServerHandlerV2());
                    }
                });
        return bootstrap;
    }

    public Channel startServer(ServerBootstrap bootstrap) {
        ChannelFuture future = bootstrap.bind(6787).syncUninterruptibly();
        return future.channel();
    }


    public static void main(String[] args) {
        SimpleNettyServer simpleNettyServer = new SimpleNettyServer();
        ServerBootstrap bootstrap = simpleNettyServer.buildBootstrap();
        Channel channel = simpleNettyServer.startServer(bootstrap);
        channel.closeFuture().syncUninterruptibly();
    }
}
