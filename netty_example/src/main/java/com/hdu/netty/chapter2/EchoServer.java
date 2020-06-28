package com.hdu.netty.chapter2;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.io.IOException;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/8/9
 * @Time 下午4:02
 */
public class EchoServer {

    private Integer port;

    public EchoServer(Integer port) {
        this.port = port;
    }


    public void start(){
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new EchoServerHandler());
                        }
                    });
            ChannelFuture future = bootstrap.bind(port).sync();
            future.channel().closeFuture().sync();
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }


    public static void main(String[] args) throws IOException, InterruptedException {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                EchoServer server = new EchoServer(1111);
                server.start();
            }
        });
        thread.start();
//        while (true) {
//            Runtime.getRuntime().exec("telnet 127.0.0.1 1111");
//            Thread.sleep(1000);
//        }
    }
}
