package com.hdu.netty.simple_netty.client;

import com.hdu.netty.simple_netty.decodeer_encoder.ByteToSimpleEntityDecoder;
import com.hdu.netty.simple_netty.decodeer_encoder.SimpleEntity;
import com.hdu.netty.simple_netty.decodeer_encoder.SimpleEntityToByteEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.nio.charset.Charset;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2019/1/21
 * @Time 下午7:38
 */
public class SimpleNettyClient {
    public static Channel channel;

    public Bootstrap buildClientBootStrap(){
        EventLoopGroup loopGroup = new NioEventLoopGroup(1);
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(loopGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE,Boolean.TRUE)
                .remoteAddress("127.0.0.1",6787)
                .handler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new SimpleEntityToByteEncoder());
                        pipeline.addLast(new ByteToSimpleEntityDecoder());
                        pipeline.addLast(new SimpleNettyClientHandler(bootstrap));
                    }
                });
        return bootstrap;
    }


    public Channel startClient(Bootstrap bootstrap){
        ChannelFuture future = bootstrap.connect().syncUninterruptibly();
        return future.channel();
    }

    public void runTask(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    if (channel != null && channel.isActive()){
                        channel.writeAndFlush(new SimpleEntity( String.valueOf(System.currentTimeMillis()),String.valueOf(System.currentTimeMillis())));
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }
    public static void run_concurrent_task(){

        SimpleThreadFactory threadFactory = new SimpleThreadFactory();
        BlockingQueue queue = new ArrayBlockingQueue(2000);
        ThreadPoolExecutor pool = new ThreadPoolExecutor(10, 10,
                1000, TimeUnit.SECONDS, queue, threadFactory, new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println("reject runnable task...");

            }
        });
        AtomicInteger count = new AtomicInteger(0);
        for (int i = 0;i < 4000; i ++){
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    SimpleNettyClient client = new SimpleNettyClient();
                    Bootstrap bootstrap = client.buildClientBootStrap();
                    channel = client.startClient(bootstrap);
                    channel.closeFuture().syncUninterruptibly();
                    System.out.println("count numer is " + count.incrementAndGet());
                }
            });
        }
        pool.shutdown();
        try {
            System.out.println("pool had shutdown");
            pool.awaitTermination(1000,TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void run_one_task(){
        SimpleNettyClient client = new SimpleNettyClient();
        client.runTask();
        Bootstrap bootstrap = client.buildClientBootStrap();
        channel = client.startClient(bootstrap);
        channel.closeFuture().syncUninterruptibly();
    }
    public static void main(String[] args){
        run_one_task();
//        run_concurrent_task();
    }






   static class SimpleThreadFactory implements ThreadFactory{

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName(String.valueOf(System.currentTimeMillis()));
            return thread;
        }
    }
}
