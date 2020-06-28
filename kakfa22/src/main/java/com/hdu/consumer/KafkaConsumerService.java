package com.hdu.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

/**
 * @Author: ssf
 * @Date: 2020/4/16 5:10 下午
 */
public class KafkaConsumerService {
    private volatile KafkaConsumer<String,String> kafkaConsumer;
    private static String TOPIC = "test.waimai_lupin_parser";

    public KafkaConsumerService() {
        Properties props = new Properties();
        props.put("bootstrap.servers","47.94.245.160:9093,47.94.245.160:9092,47.94.245.160:9094");
        props.put("group.id","testConsumer");
        props.put("auto.offset.reset","earliest");
        props.put("auto.commit.interval.ms", "1000");
        props.put("client.id", "test_client_id");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        this.kafkaConsumer = new KafkaConsumer<String, String>(props);

    }

    public void receiveMessage(){
        kafkaConsumer.subscribe(Collections.singletonList(TOPIC));
        try {
            while (true){
                System.out.println("begin...");
                this.kafkaConsumer.listTopics().forEach((k,v)->{
                    System.out.println("k " + k + ",v " + v);
                });
                ConsumerRecords<String,String> records = this.kafkaConsumer.poll(Duration.ofMillis(300));
                records.forEach(t->{
                    System.out.println(t.toString());
                });
            }
        }catch (Exception e){
            e.printStackTrace();
            kafkaConsumer.close();
        }
    }
}
