package com.hdu.offheap;

import java.nio.ByteBuffer;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/12/26
 * @Time 下午5:40
 */
public class HandleOffHeapWithByteBuffer {
    public static void main(String[] args){
        ByteBuffer buffer = ByteBuffer.allocateDirect(1000*1024*1024);
        for (;;){
            buffer.put("hhhhhhhhhaahaahahahah".getBytes());
        }
    }
}
