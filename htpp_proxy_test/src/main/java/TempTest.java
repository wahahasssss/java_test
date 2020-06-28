import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.config.SocketConfig;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2019/4/25
 * @Time 11:23 AM
 */
public class TempTest {
    public static void main(String[] args) throws IOException {
        BasicCredentialsProvider credsProvider = new BasicCredentialsProvider();
        HttpHost proxy = new HttpHost("114.67.80.9",20001,"http");

        credsProvider.setCredentials(new AuthScope(proxy.getHostName(), proxy.getPort()),
                new UsernamePasswordCredentials("meituanwaimai", "waimaisankuai"));

        HttpClient client = HttpClientBuilder.create().setDefaultCredentialsProvider(credsProvider).build();
        SocketConfig socketConfig = SocketConfig.custom().setSoTimeout(5000*10).build();
        RequestConfig requestConfig =  RequestConfig.custom()
                .setProxy(proxy)  // 设置 HttpHost 代理
                .setConnectTimeout(1000*5)  // 设置连接到服务器的时间
                .setConnectionRequestTimeout(1000*5)  // 设置获取 connection 的时间
                .setSocketTimeout(5000*10)  // 设置获取数据的时间
                .build();

        HttpGet get = new HttpGet("http://47.94.245.160:8080/api/headers");
        get.setConfig(requestConfig);
        CloseableHttpResponse response = (CloseableHttpResponse) client.execute(get);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }
}
