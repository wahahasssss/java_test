import com.alibaba.fastjson.JSON;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @Author: ssf
 * @Date: 2019/11/28 10:47 上午
 */
public class GetBody {
    public static void main(String[] args) {

        String content = "{\"tecInfoAdmin\":\"shanbinbin\",\"dimDesc\":\"广告站外-频道\",\"infoStatus\":1,\"classifyId\":2607,\"dimType\":1,\"initiator\":\"shanbinbin\",\"applyBackground\":\"\",\"domainId\":4,\"tableName\":\"\",\"topicId\":2584,\"dimNameEnCode\":\"dsp_slot_channel_id\",\"attributeType\":1,\"columnIds\":[],\"appId\":9,\"dimNameEnValue\":\"dsp_slot_channel_name\",\"topicName\":\"广告\",\"dimNameEn\":\"dsp_slot_channel\",\"classifyName\":\"业务类型\",\"dimNameCn\":\"站外-频道\",\"dimTableMappingPO\":{\"dimCondition\":\"\",\"tableType\":1,\"dimValuesFile\":\"{}\",\"dimValueCondition\":\"\",\"codeValue\":\"\"}}";
        Object results = JSON.parseObject(content);
        System.out.println(results);

    }
}
