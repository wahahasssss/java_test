package com.hdu;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/9/25
 * @Time 下午5:11
 */
public class Main {
    public static void main(String[] args) throws NoSuchMethodException {
//        TestClass test = new TestClass();
//        Class clazz = TestClass.class;
//        boolean flag = TestClass.class.isAnnotationPresent(UserName.class);
//        if (flag){
//            System.out.println("has annotation");
//        }else {
//            System.out.println("has no annotation");
//        }
//        Method method = clazz.getDeclaredMethod("test");
//        UserName userName = method.getAnnotation(UserName.class);
//        String jsonString = "{\"url\": \"\", \"basePath\": \"$\", \"kvs\": {\"highOpinion\": \"$.highOpinion\", \"venderId\": \"$.venderId\", \"stockCount\": \"$.stockCount\", \"isStoreVip\": \"$.isStoreVip\", \"skuName\": \"$.skuName\", \"mkPrice\": \"$.mkPrice\", \"realTimePrice\": \"$.realTimePrice\", \"cateid\": \"$.userActionSku.loads().cateid\", \"solution\": \"$.userActionSku.loads().solution\", \"store_id\": \"$.userActionSku.loads().store_id\"}, \"method\": \"\"}\n";

        String jsonString = "{\"售后单号\":\"197106768\",\"订单号\":\"1209628129\",\"商户id\":\"7198054\",\"销售姓名\":\"李杰\",\"商品名称\":\"小米椒 红\",\"兜底报销金额\":\"60.0\",\"实际兜底量\":\"2.0\",\"单价差\":\"15.81\",\"溢价率\":\"1.1141649\",\"发起原因\":\"商品未配送需要急用\",\"近7天同商户同销售同商品兜底次数\":\"1\"}";
        HashMap map1 = JSON.parseObject(jsonString, HashMap.class);
        System.out.println(map1.get("售后单号"));

    }
}
