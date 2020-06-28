package com.hdu.netty.udp;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.net.URISyntaxException;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2019/1/14
 * @Time 下午7:22
 */
public class LogEventBroadcaster {
    private final EventLoopGroup bossGroup;
    private final EventLoopGroup workerGroup;
    private final Bootstrap bootstrap;
    private final File file;

    public LogEventBroadcaster(InetSocketAddress address, File file) {
        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        bootstrap.group(workerGroup).channel(NioDatagramChannel.class)
                .option(ChannelOption.SO_BROADCAST,true)
                .handler(new LogEventEncoder(address));
        this.file = file;
    }

    public void startBroadcaster() throws InterruptedException, IOException {
        Channel channel = bootstrap.bind(0).sync().channel();
        long pointer = 0;
        for (;;){
            long len = file.length();
            if (len < pointer){
                pointer = len;
            }else if (len > pointer){
                RandomAccessFile randomAccessFile = new RandomAccessFile(file,"r");
                randomAccessFile.seek(pointer);
                String line;
                while ((line = randomAccessFile.readLine())!=null){
                    System.out.println(line);
                    channel.writeAndFlush(new LogEvent(null,file.getAbsolutePath(),line,-1));
                }
                pointer = randomAccessFile.getFilePointer();
                randomAccessFile.close();
                pointer = 0;
            }
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){
                Thread.interrupted();
                break;
            }
        }
    }

    public void stop(){
        workerGroup.shutdownGracefully();
        bossGroup.shutdownGracefully();
    }

    public static void main(String[] args) throws URISyntaxException, FileNotFoundException {
        File file = new File(LogEventBroadcaster.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath() + "loc.csv");
        LogEventBroadcaster logEventBroadcaster = new LogEventBroadcaster(
                new InetSocketAddress("255.255.255.255",9999),file);
        try {
            logEventBroadcaster.startBroadcaster();
        }catch (Exception e){
            e.printStackTrace();
            logEventBroadcaster.stop();
        }
    }
}
