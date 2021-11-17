package com.hdu.netty.simple_http.handler;

import com.alibaba.fastjson.JSON;
import com.hdu.netty.websocket.chatroom.HttpRequestHandler;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.DecoderResult;
import io.netty.handler.codec.http.*;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2019/1/23
 * @Time 上午10:05
 */
public class SimpleHttpRequestHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest msg) throws Exception {
        if (msg.uri().equalsIgnoreCase("/headers")) {
            System.out.println("headers=====???????");
            HashMap<Object, Object> result = new HashMap<>();
            for (Map.Entry entry : msg.headers().entries()) {
                result.put(entry.getKey(), entry.getValue());
            }

            HttpResponse response = new DefaultHttpResponse(HttpVersion.HTTP_1_1,
                    HttpResponseStatus.OK);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "json/application; charset=UTF-8");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, JSON.toJSONString(result).toCharArray().length);
            response.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
            ctx.write(response);
            ctx.write(Unpooled.copiedBuffer(JSON.toJSONString(result).toCharArray(), Charset.defaultCharset()));
            ctx.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT);
        } else if (msg.uri().equalsIgnoreCase("/headers1")) {
//            ctx.fireChannelRead(msg);
            HashMap<String, String> data = new HashMap<>();
            data.put("data", "323232323");

            HttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.copiedBuffer(JSON.toJSONString(data).toCharArray(), Charset.defaultCharset()));
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "json/application; charset=UTF-8");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, JSON.toJSONString(data).toCharArray().length);
            response.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
            ctx.write(response);
            ctx.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT);
        } else {
            msg.retain();
            ctx.fireChannelRead(msg);
        }
    }
}

