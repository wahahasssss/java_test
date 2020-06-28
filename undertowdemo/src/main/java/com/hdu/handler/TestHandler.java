package com.hdu.handler;

import com.alibaba.fastjson.JSON;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HeaderMap;
import io.undertow.util.Headers;
import io.undertow.util.HttpString;

import java.util.HashMap;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/4/17
 * @Time 下午2:54
 */
public class TestHandler implements HttpHandler{
    public void handleRequest(HttpServerExchange httpServerExchange) throws Exception {
        ResponseModel response = new ResponseModel();
        switch (httpServerExchange.getRequestPath()){
            case "/api/headers": {
                HashMap<String,String> header = new HashMap<>();
                HeaderMap map = httpServerExchange.getRequestHeaders();
                for (HttpString key:map.getHeaderNames()){
                    header.put(key.toString(),map.get(key,0).toString());
                }
                response.setCode(200);
                response.setHeaders(header);
                break;
            }
            default:{
                break;
            }
        }
        httpServerExchange.getResponseHeaders().put(Headers.CONTENT_TYPE,"application/json");
        httpServerExchange.getResponseSender().send(JSON.toJSONString(response));

    }
}
