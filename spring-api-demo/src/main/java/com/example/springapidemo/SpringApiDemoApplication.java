package com.example.springapidemo;

import com.alibaba.fastjson.JSON;
import com.amazonaws.util.json.Jackson;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

@SpringBootApplication
public class SpringApiDemoApplication {

    public static void main(String[] args) throws IOException {
//		GravityCallBackDto param = new GravityCallBackDto();
//		param.setAppId(1);
//		String jsonStr = JSON.toJSONString(param);
//		HashMap<String,Object> maps = new HashMap<>();
//		maps.put("one","1");
//		param.setCustomVariables(jsonStr);
//		HttpClient client = HttpClients.createDefault();
//		HttpPost post = new HttpPost();
//		post.setURI(URI.create("http://127.0.0.1:8080/external/4/1/callback"));
//		post.setHeader("Cookie","_lxsdk_cuid=16a962a8b18c8-0b6d18c25262ce-37677603-13c680-16a962a8b19c8; _lxsdk=16a962a8b18c8-0b6d18c25262ce-37677603-13c680-16a962a8b19c8; com.sankuai.waimai.d.datahub_ssoid=eAHjYBTY9LGNUaHrzY9lW3WNZJLzc_WKE_OySxMz9coTM3OBVIpeSmJJYkZpkpWCkZmReZqppYmxeXKaialpSqKRWbKWgXGSpUmaUYplYqqTpcKSh2eP7dDVYDQiqNgCZLEDk8ekiy9bt-hGKSRZAo1JTks1M9VKNDFMBXJNk0yMU40MDdMSE5ONTQB_SjgA**eAEFwYEBgDAIA7CXKoLAOYO1_59gMlvKzTXwPkqEIztC8qLpvgedDlWTNN4YP98G2WmDtfgBTycSKA; _lxsdk_s=16b4fe313c8-c94-85d-e6f%7C%7C2");
//		post.setHeader("Content-type","application/json");
//		String paramsStr = JSON.toJSONString(param);
//		HttpEntity entity = new StringEntity(paramsStr);
//		post.setEntity(entity);
//		CloseableHttpResponse response = (CloseableHttpResponse) client.execute(post);
//		HashMap<Integer,Object> map= new HashMap<>();
//		map.put(1,"two");
//		map.put(2,"zz");
//		System.out.println(Jackson.toJsonString(map));
        SpringApplication.run(SpringApiDemoApplication.class, args);
//        List<Integer> list = Arrays.asList(1, 2, 3);
//        list.forEach(t -> {
//            System.out.println(t);
//        });
//        for (Integer i : list) {
//            System.out.println(i);
//        }


    }

}
