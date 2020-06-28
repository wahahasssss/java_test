package com.example.springapidemo.controller;

import com.sankuai.waimai.lupin.client.core.LupinFetcherClient;
import com.sankuai.waimai.lupin.client.handle.AbstractResultHandle;
import com.sankuai.waimai.lupin.common.constant.LupinConstant;
import com.sankuai.waimai.lupin.common.fetch.FetchTargetInfo;
import com.sankuai.waimai.lupin.common.fetch.LupinFetchContext;
import com.sankuai.waimai.lupin.common.http.HttpMethod;
import com.sankuai.waimai.lupin.common.http.request.DefaultHttpMessage;
import com.sankuai.waimai.lupin.common.http.request.DefaultHttpRequest;
import com.sankuai.waimai.lupin.common.message.IpSlotInfoBean;
import com.sankuai.waimai.lupin.common.message.TaskFetcherActionResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: caoluyang
 * @Date: 2019/4/25 下午5:26
 */
@RestController
@RequestMapping("/crawlTest")
public class TestTaobaoController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    public class DemoResultHandler extends AbstractResultHandle {
        @Override
        public void onSuccess(TaskFetcherActionResponseMessage message) {

            System.out.println("fetch success" + message.getFetchResult());
        }

        @Override
        public void onFailure(TaskFetcherActionResponseMessage message) {
            System.out.println("fetch failure");
        }

        @Override
        public void onNotFetch(TaskFetcherActionResponseMessage message) {
            System.out.println("on not fetch");
        }

        @Override
        public void onTimeOut(TaskFetcherActionResponseMessage message) {
            System.out.println("fetch timeout");
        }
    }

    @ResponseBody
    @RequestMapping("/hello")
    public String test() {
        return "hello";
    }

    @ResponseBody
    @RequestMapping("/taobao")
    public String driver() {
        try {
            LupinFetcherClient lupinFetcherClient = LupinFetcherClient.getInstance("10.4.14.115", 8412);

            FetchTargetInfo fetchTargetInfo = new FetchTargetInfo();
            fetchTargetInfo.setTarget("meituan");
            fetchTargetInfo.setAppKey("meituan.qcs.data.mappers.statistic");
            fetchTargetInfo.setModel("wikiTest");
            fetchTargetInfo.setGzip(false);
            fetchTargetInfo.setGzipCode("ISO-8859-1");
            fetchTargetInfo.setQps(1);
            fetchTargetInfo.setReturnClient(true);
            fetchTargetInfo.setStoreType(LupinConstant.DataStore.CLIENT);

            LupinFetchContext lupinFetchContext = new LupinFetchContext();
            lupinFetchContext.setFetchTaskInfo(fetchTargetInfo);

            DemoResultHandler handler = new DemoResultHandler();

            lupinFetcherClient.preLoad(lupinFetchContext, handler);

            Map<Object, Object> userparam = new HashMap<>();

            DefaultHttpRequest request = httpPostRequest();

            String url = "https://www.taobao.com/help/getip.php";

            lupinFetcherClient.submitTask(lupinFetchContext, url, userparam, request);

            try {
                Thread.sleep(10 * 1000);
            } catch (InterruptedException e) {
                System.out.println(e);
            }

            lupinFetcherClient.releaseTargetModelQps(lupinFetchContext);
//            lupinFetcherClient.destroy();
            return "ok";
        } catch (Exception e) {
            LOGGER.error("error,cityName={}", e);
            return "error";
        }

    }

    public static DefaultHttpRequest httpGetRequet() {
        DefaultHttpRequest request = new DefaultHttpRequest();
        request.setMethod(HttpMethod.GET);
        request.setHeaderAcceptLanguage("zh-CN,zh;q=111111");
        /**  指定城市抓取  **/
//        request.setCityTags(Arrays.asList("430400"));
        /**  指定城市抓取  **/
        return request;
    }

    public static DefaultHttpRequest httpGetRequet(List<IpSlotInfoBean> slotInfoBeans) {
        DefaultHttpRequest request = new DefaultHttpRequest();
        request.setMethod(HttpMethod.GET);
        request.setHeaderAcceptLanguage("zh-CN,zh;q=111111");
        //绑定指定IP抓取
        request.setIpSlotInfoBean(slotInfoBeans.get(0));
        return request;
    }

    public static DefaultHttpRequest httpPostRequest() {
        DefaultHttpRequest request = new DefaultHttpRequest();
        request.setMethod(HttpMethod.POST);
        request.setBody("this si str");
        request.setPostBodyType(DefaultHttpMessage.PostBodyType.STRINGENTITY);
        return request;
    }


} 