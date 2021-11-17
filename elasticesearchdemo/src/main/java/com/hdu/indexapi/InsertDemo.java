package com.hdu.indexapi;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * @author shushoufu
 * @date 2020/07/23
 **/
public class InsertDemo {
    private static RestHighLevelClient restHighLevelClient;
    private static List<String> cities = Arrays.asList("成都", "北京", "上海");
    private static List<String> region = Arrays.asList("成都大区", "湖北省区", "济南大区");
    private static List<String> chars = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i");
    private static List<String> mainCities = Arrays.asList("成都", "北京");
    private static List<String> ips = Arrays.asList("60.174.169.26", "233.128.56.68", "46.245.160.32");
    private static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    //    private static List<String> ips = Arrays.asList("60.174.169.26");
    private static String test_index = "rcs_event_test";
    private static String stage_index = "rcs_event_stage";

    private static String visit_index = "rcs_visit_detail";

    public static void main(String[] args) throws IOException {

        String[] node = "192.168.2.10,192.168.2.13,192.168.2.14".split(",");
//        String[] node = "10.2.2.31".split(",");
        HttpHost[] nodeArr = new HttpHost[node.length];
        for (int i = 0; i < nodeArr.length; i++) {
            nodeArr[i] = new HttpHost(node[i], Integer.parseInt("29200"), "http");
//            nodeArr[i] = new HttpHost(node[i], Integer.parseInt("7800"), "http");
        }
        restHighLevelClient = new RestHighLevelClient(RestClient.builder(nodeArr));

//        insertData(1000, restHighLevelClient, visit_index);
//        bulkInsertData();
        insertOneData(restHighLevelClient, "mapping_passport_detail");
        restHighLevelClient.close();
    }

    private static void insertOneData(RestHighLevelClient client, String index) throws IOException {
        DemoTo demoTo = buildTo(2);
        IndexRequest indexRequest = new IndexRequest(index).id(String.valueOf(14284742))
                .source("{\"company_ids\": \"11448997:1:0_10970338:1:0_41189312:1:0_70693184:-1:0\",\"passport_id\": \"14284742\"}", XContentType.JSON).type("product");
        client.index(indexRequest);
        System.out.println("INSERT " + JSON.toJSONString(demoTo));
    }

    private static void insertData(Integer limit, RestHighLevelClient client, String index) throws IOException {
        for (int i = 0; i < limit; i++) {
//            DemoTo demoTo = buildTo(i);
            VisitTo visitTo = buildVisitTo(i);
            IndexRequest indexRequest = new IndexRequest(index).id(String.valueOf(i))
                    .source(JSON.toJSONString(visitTo), XContentType.JSON).type("product");
            client.index(indexRequest);
            System.out.println("INSERT " + JSON.toJSONString(visitTo));
        }
    }

    private static void bulkInsertData(Integer batchLimit, Integer batch, RestHighLevelClient client, String index) throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        for (int i = 0; i < batchLimit; i++) {
            int count = batch * batchLimit + i;
            DemoTo demoTo = buildTo(count);
            IndexRequest indexRequest = new IndexRequest(index).id(String.valueOf(count))
                    .source(JSON.toJSONString(demoTo), XContentType.JSON).type("product");
            bulkRequest.add(indexRequest);
            System.out.println("INSERT " + JSON.toJSONString(demoTo));
        }
        BulkResponse responses = client.bulk(bulkRequest);
        for (BulkItemResponse res : responses.getItems()) {
            System.out.println("Bulk Insert Result is " + res.isFailed());
        }
    }

    private static DemoTo buildTo(Integer i) {
        DemoTo demoTo = new DemoTo();
        demoTo.setIp(ips.get(new Random().nextInt(ips.size())));
        demoTo.setCompanyId("123456");
        demoTo.setPassportId("987654");
        demoTo.setDeviceId("asd89asd123123");
        demoTo.setPhone("13012345678");
        demoTo.setAddress("北京市朝阳区");
        demoTo.setOpenId("ab9x56fasd");
        demoTo.setDt(20200727L);
        demoTo.setCity(cities.get(new Random().nextInt(cities.size())));
        demoTo.setRegion(region.get(new Random().nextInt(region.size())));
        demoTo.setD_id(randomString(4));
        demoTo.setId(i);
        return demoTo;
    }

    private static VisitTo buildVisitTo(Integer i) {
        LocalDateTime now = LocalDateTime.now();
        VisitTo visitTo = VisitTo.builder()
                .visit_id("visit_id_" + i)
                .bd_id("bd_id_" + i)
                .city(cities.get(new Random().nextInt(cities.size())))
                .region(region.get(new Random().nextInt(region.size())))
                .main_city_name(mainCities.get(new Random().nextInt(mainCities.size())))
                .dt(20200928L)
                .company_id("company_id_" + i)
                .company_phone("company_phone_" + i)
                .visit_name("visit_name_" + i)
                .visit_time(now.format(FORMATTER))
                .id(UUID.randomUUID().toString())
                .bus_time(now.format(FORMATTER))
                .ip(ips.get(new Random().nextInt(ips.size())))
                .build();
        return visitTo;
    }

    private static String randomString(Integer length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(chars.get(new Random().nextInt(chars.size())));
        }
        return sb.toString();
    }
}
