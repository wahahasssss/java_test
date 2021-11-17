package com.hdu;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509ExtendedKeyManager;
import javax.net.ssl.X509TrustManager;
import java.io.*;
import java.net.Socket;
import java.net.URI;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/5/24
 * @Time 下午4:14
 */
public class HttpResultDemoUtil {


    public static String doHttpPost(String url) {
        String result = "";
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
//        post.setHeader("Content-Type","application/x-www-form-urlencoded");
//        post.setHeader("User-Agent","Firefox");
        CloseableHttpResponse response = null;
        InputStream inputStream = null;
        try {
            response = closeableHttpClient.execute(post);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                inputStream = entity.getContent();
                result = IOUtils.toString(inputStream);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (response != null) {
                try {
                    response.close();
                } catch (Exception E) {
                    E.printStackTrace();
                }

            }
        }

        return result;
    }

    public static String doHttpsPost(String url) throws IOException, NoSuchAlgorithmException, KeyManagementException {
        SSLContext sc = SSLContext.getInstance("SSL");
        X509TrustManager manager = new X509TrustManager() {
            public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

            }

            public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

            }

            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        };
        sc.init(null, new TrustManager[]{manager}, null);

        String result = "";
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", new SSLConnectionSocketFactory(sc))
                .build();
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);

        CloseableHttpClient closeableHttpClient = HttpClients.custom().setConnectionManager(connectionManager).build();
//        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        post.setHeader("Content-Type", "application/x-www-form-urlencoded");
        post.setHeader("User-Agent", "Firefox");
        CloseableHttpResponse response = closeableHttpClient.execute(post);
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            result = EntityUtils.toString(entity);
        }
        System.out.println(result);
        return result;
    }


    public static String doHttpsPost1(String url) throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException, KeyManagementException {
        String result = "";

        KeyStore trueStore = KeyStore.getInstance(KeyStore.getDefaultType());
        FileInputStream inputStream = new FileInputStream(new File("/Users/shushoufu/Desktop/java_test/HttpRequest/src/main/resources/keystore.jks"));
        try {
            trueStore.load(inputStream, "123456".toCharArray());
        } finally {
            inputStream.close();
        }
        SSLContext sslContext = SSLContexts.custom()
                .loadTrustMaterial(trueStore, new TrustSelfSignedStrategy())
                .build();
        SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(
                sslContext,
                new String[]{"TTSv1"},
                null,
                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER
        );
        CloseableHttpClient closeableHttpClient = HttpClients.custom()
                .setSSLSocketFactory(sslConnectionSocketFactory)
                .build();
//        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        post.setHeader("Content-Type", "application/x-www-form-urlencoded");
        post.setHeader("User-Agent", "Firefox");
        CloseableHttpResponse response = closeableHttpClient.execute(post);
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            result = EntityUtils.toString(entity);
        }
        System.out.println(result);
        return result;
    }

    public void doHttpsPostWithTrueAllCer(String url) {
        TrustManager[] trustAllCerts = new TrustManager[1];
        TrustManager tm = new CustomTrustManager();
    }


    static class CustomTrustManager implements TrustManager, X509TrustManager {
        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
            return;
        }

        public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
            return;
        }

        public boolean isServerTrusted(X509Certificate[] certs) {
            return true;
        }

        public boolean isClientTrusted(X509Certificate[] certs) {
            return true;
        }

        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

    }
}
