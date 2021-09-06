package com.hdu.indexapi;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;

/**
 * @author shushoufu
 * @date 2020/08/12
 **/
public class SearchDemo {
    private static RestHighLevelClient restHighLevelClient;
    private static String test_index = "rcs_event_test";
    private static String stage_index = "rcs_event_stage";
    public static void main(String[] args) throws IOException {
        String[] node = "192.168.2.10,192.168.2.13,192.168.2.14".split(",");
//        String[] node = "10.2.2.31".split(",");
        HttpHost[] nodeArr = new HttpHost[node.length];
        for (int i = 0; i < nodeArr.length; i++) {
            nodeArr[i] = new HttpHost(node[i], Integer.parseInt("29200"), "http");
//            nodeArr[i] = new HttpHost(node[i], Integer.parseInt("7800"), "http");
        }
        restHighLevelClient = new RestHighLevelClient(RestClient.builder(nodeArr));
        searchAnything(restHighLevelClient, "decision_new_log");
    }

    public static void searchAnything(RestHighLevelClient client, String index) throws IOException {
        SearchRequest request = new SearchRequest(index);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.matchQuery("businessScenarioKey", "salespromotion"))
                .must(QueryBuilders.matchPhraseQuery("decisionResult","pass"));

        sourceBuilder.query(boolQueryBuilder);
        sourceBuilder.size(20);
        request.source(sourceBuilder);
        request.types("product");
        SearchResponse response = client.search(request);
        for (SearchHit hit:response.getHits()){
            System.out.println(hit.getSourceAsString());
        }
    }

}
