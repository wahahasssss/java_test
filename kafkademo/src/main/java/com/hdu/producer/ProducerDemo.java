package com.hdu.producer;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2019/3/21
 * @Time 3:36 PM
 */
public class ProducerDemo {
    private Producer<String,String> producer;
    private String topic;
    private volatile AtomicInteger count = new AtomicInteger(0);

    public ProducerDemo(){
        topic = "test.waimai_lupin_parser";
        Properties kafkaProperties = new Properties();
        kafkaProperties.put("bootstrap.servers","47.94.245.160:9093,47.94.245.160:9092,47.94.245.160:9094");
        kafkaProperties.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        kafkaProperties.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
        kafkaProperties.put("compression.type","gzip");
        kafkaProperties.put("retries",3);
        kafkaProperties.put("timeout.ms",10000);
        kafkaProperties.put("acks","1");
        kafkaProperties.put("batch.size",163840);
        kafkaProperties.put("linger.ms",100);
        kafkaProperties.put("max.request.size","2097152");
        producer = new KafkaProducer<String, String>(kafkaProperties);
    }

    public void run(){
        for (int i = 0; i< 100000;i++){
            String value = "{\n" +
                    "    \"postman-token\": \"fee40a89-956d-4031-865f-1247e227a7b3\"," +
                    "    \"host\": \"47.94.245.160:8080\"," +
                    "    \"connection\": \"keep-alive\"," +
                    "    \"cache-control\": \"no-cache\"," +
                    "    \"accept-encoding\": \"gzip, deflate\"," +
                    "    \"ts\": \""+System.currentTimeMillis()+"\"," +
                    "    \"user-agent\": \"PostmanRuntime/7.1.1\"," +
                    "    \"accept\": \"*/*\"" +
                    "}";
            producer.send(new ProducerRecord<>(topic,value),new WriteKafkaCallBack());
        }

    }
    class WriteKafkaCallBack implements Callback {

        @Override
        public void onCompletion(RecordMetadata recordMetadata, Exception e) {
            if(e != null) {
                System.out.println(String.format("write topic: %s fail,exception: %s",topic,e.getMessage()));
            }else {
                System.out.println(String.format("send msg success ,count is %d",count.incrementAndGet()));
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        ProducerDemo producerDemo = new ProducerDemo();
        producerDemo.run();
        Thread.sleep(100000);
    }
}
