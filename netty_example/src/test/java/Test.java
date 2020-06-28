import io.netty.buffer.*;
import io.netty.channel.ChannelPromise;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/3/27
 * @Time 下午7:20
 */
public class Test {
    public static void main(String[] args){
//        ByteBuf buf = Unpooled.copiedBuffer("this is a test", Charset.forName("UTF-8"));
//        ByteBuf sliced = buf.slice(0,4);
//        System.out.println(sliced.toString(Charset.forName("utf-8")));
//        buf.setByte(0,(byte)'j');
//        System.out.println(String.format("buf first byte is %c,sliced first byte is %c",buf.getByte(0),sliced.getByte(0)));
//        assert buf.getByte(0) == sliced.getByte(0);

//        ByteBufAllocator alloc =  new PooledByteBufAllocator();
//        ByteBuf buf = alloc.buffer(4);
//        if (buf.writableBytes()>=4){
//            buf.writeInt(4);
//        }
//        System.out.println(buf.readInt());

//        ByteBuf buf = Unpooled.buffer(4);
//        if (buf.writableBytes()>=4){
//            buf.writeInt(4);
//        }
//        System.out.println(buf.readInt());
//
//        ByteBuf buf = Unpooled.copiedBuffer("this is my world",Charset.forName("utf-8"));
//        System.out.println(ByteBufUtil.hexDump(buf));


        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 1000;i++){
            list.add(i);
        }
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism","10");
        list.parallelStream().forEach((i)->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(String.format("thread is is %s,%d",Thread.currentThread().getName(),i));
        });
    }


}
