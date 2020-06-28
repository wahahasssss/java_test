package com.hdu;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/8/8
 * @Time 下午5:11
 */
public class VolatileTest {
    private static volatile MyStatusEntity myData;

    public static void main(String[] args){
        myData = new MyStatusEntity("ssf",12,"1234");

        System.out.println(myData);
        Thread thread = new Thread(new Runnable() {
            public void run() {
                myData.setAge(111);
                System.out.println(myData.toString());
            }
        });
        thread.start();

        System.out.println(">>>>>>>>>>>>>>>>>>>");
        System.out.println(myData);
    }
}
