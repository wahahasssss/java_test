package com.hdu;

import com.hdu.Annation.UserName;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/9/25
 * @Time 下午5:11
 */
public class Main {
    public static void main(String[] args) throws NoSuchMethodException {
        TestClass test = new TestClass();
        Class clazz = TestClass.class;
        boolean flag = TestClass.class.isAnnotationPresent(UserName.class);
        if (flag){
            System.out.println("has annotation");
        }else {
            System.out.println("has no annotation");
        }
        Method method = clazz.getDeclaredMethod("test");
        UserName userName = method.getAnnotation(UserName.class);
        String jsonString = "{\"url\": \"\", \"basePath\": \"$\", \"kvs\": {\"highOpinion\": \"$.highOpinion\", \"venderId\": \"$.venderId\", \"stockCount\": \"$.stockCount\", \"isStoreVip\": \"$.isStoreVip\", \"skuName\": \"$.skuName\", \"mkPrice\": \"$.mkPrice\", \"realTimePrice\": \"$.realTimePrice\", \"cateid\": \"$.userActionSku.loads().cateid\", \"solution\": \"$.userActionSku.loads().solution\", \"store_id\": \"$.userActionSku.loads().store_id\"}, \"method\": \"\"}\n";

        System.out.println(userName);
    }
}
