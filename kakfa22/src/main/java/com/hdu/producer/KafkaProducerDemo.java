package com.hdu.producer;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2019/4/30
 * @Time 3:29 PM
 */
public class KafkaProducerDemo {

    public static void main(String[] args) throws InterruptedException {

        KafkaProducerService producerService = new KafkaProducerService();
        producerService.sendBatchMessage(1000);
        Thread.sleep(10000);
    }
}
