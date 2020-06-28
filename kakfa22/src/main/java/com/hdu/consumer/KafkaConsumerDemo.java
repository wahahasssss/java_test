package com.hdu.consumer;

/**
 * @Author: ssf
 * @Date: 2020/4/16 5:21 下午
 */
public class KafkaConsumerDemo {
    public static void main(String[] args){
        KafkaConsumerService consumerService = new KafkaConsumerService();
        consumerService.receiveMessage();
    }
}
