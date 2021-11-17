import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2019/4/10
 * @Time 12:21 PM
 */
public class ProxyTst {
    private static String domain = "62.234.21.179";
    private static String userName = "lupin_proxy";
    private static String password = "lupin_proxy";

    private static String getIHeaders(Integer port) throws IOException {
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(
                new AuthScope(domain, port),
                new UsernamePasswordCredentials(userName, password));
        HttpHost proxy = new HttpHost(domain, port, "http");
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultCredentialsProvider(credentialsProvider).build();
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        HttpGet get = new HttpGet("https://api.udache.com/gulfstream/passenger/v2/core/pMultiEstimatePrice?cancel=test6daf768981fff0469e2828b85154467b&map_type=soso&origin_id=1&too_far_order_limit=1&idfa=&maptype=soso&shake_flag=0&shift_time=0&call_car_type=0&platform_type=2&degrade_type=0&order_type=0&channel=215&android_id=f3d30f8c25b220f0&networkType=WIFI&willing_wait=0&to_poi_type=hd&uuid=F8C6CA8E7B324F1CD5F23725B3409886&suuid=8F71C98F1E3FC09B42B2BAC9F6EC4A01_215&from_poi_type=cfcross_first&vcode=514&to_area=1&user_type=0&datatype=1&carpool_seat_num=1&token=&scene_type=0&client_type=1&appversion=5.2.43&lang=zh-CN&anycar_carpool_seat_num=1&sub_menu_id=now&feature_enable=57&menu_id=flash&access_key_id=2&data_type=android&terminal_id=1&os=5.1&app_version=5.2.43&from_area=1&reestimate_ability=1&brand=Meizu&sig=e2c32ec6344de2006e19304d8c4ce9afd115b411&model=m1%20note&require_level=600&from_lng=118.793668&from_lat=32.045173&to_lng=118.719037&to_lat=31.966676");
        HttpGet get = new HttpGet("http://47.94.245.160:8181/api/headers");
        RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
//        RequestConfig config = RequestConfig.custom().build();
        get.setConfig(config);
        CloseableHttpResponse response = httpClient.execute(get);
        String content = EntityUtils.toString(response.getEntity());
        return content;
    }

    private static void main() throws IOException {
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(
                new AuthScope("62.234.21.179", 3389),
                new UsernamePasswordCredentials("lupin_proxy", "lupin_proxy"));
        HttpHost proxy = new HttpHost("62.234.21.179", 3389, "http");
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultCredentialsProvider(credentialsProvider).build();
        HttpGet get = new HttpGet("https://myip.ipip.net");
        RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
        get.setConfig(config);
        CloseableHttpResponse response = httpClient.execute(get);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }

    public static void main(String[] args) throws IOException {
        main();
        System.out.println(getIHeaders(3389));

    }
}
