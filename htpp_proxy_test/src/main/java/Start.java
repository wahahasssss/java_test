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
import java.util.*;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/3/12
 * @Time 下午5:09
 */
public class Start {
    public static void main(String[] args) throws IOException {
//        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism","1");
//        RoaringBitmap bitmap = new RoaringBitmap();
////        bitmap.add(1);
//        for (int start = 0;start < 1000; start ++){
//            bitmap.add(start + 1);
//        }
//        System.out.println(String.format("the bitmap size is {}",bitmap.getSizeInBytes()));
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(
                new AuthScope("47.94.245.160",5647),
                new UsernamePasswordCredentials("ssf","12345678'"));
        HttpHost proxy = new HttpHost("47.94.245.160",5647,"http");
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultCredentialsProvider(credentialsProvider).build();
        HttpGet get = new HttpGet("https://www.baidu.com");
        RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
        get.setConfig(config);
        CloseableHttpResponse response = httpClient.execute(get);
        System.out.println(EntityUtils.toString(response.getEntity()));
//        ArrayList<Integer> ccc = new ArrayList<>();
//        for(int i = 0; i < 1000; i ++){
//            ccc.add(10 + i);
//        }
//        HashSet<Long> threads = new HashSet<>();
//        long start = System.currentTimeMillis();
//        ccc.parallelStream().forEach(p -> {
//            threads.add(Thread.currentThread().getId());
//            System.out.println(String.format("current thread is %s,p is %d",Thread.currentThread().getId(),p));
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//        System.out.println(String.format("the cost time is %d",System.currentTimeMillis() - start));
//
//        System.out.println(String.format("thread count is %d",threads.size()));
}
}
