package com.hdu.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: ssf
 * @Date: 2020/4/16 5:10 下午
 */
public class KafkaConsumerService {
    private volatile KafkaConsumer<String,String> kafkaConsumer;
//    private static String TOPIC = "test.waimai_lupin_parser";
    private static String TOPIC = "TestComposeTopic";
    public KafkaConsumerService() {
        Properties props = new Properties();
        String ip = "172.31.196.186";
        props.put("bootstrap.servers",String.format("%s:9092,%s:9093,%s:9094", ip, ip, ip) );// 这是 docker容器对应
//        props.put("bootstrap.servers","47.94.245.160:9093,47.94.245.160:9092,47.94.245.160:9094");
        props.put("group.id","testConsumer6");
        props.put("auto.offset.reset","earliest");
//        props.put("auto.commit.interval.ms", "1000");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        props.put("client.id", "test_client_id6");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        this.kafkaConsumer = new KafkaConsumer<String, String>(props);

    }

    public void receiveMessage(){
        AtomicInteger count = new AtomicInteger(0);
        kafkaConsumer.subscribe(Collections.singletonList(TOPIC));
        try {
            while (true){

//                this.kafkaConsumer.listTopics().forEach((k,v)->{
////                    System.out.println("k " + k + ",v " + v);
//                });
                ConsumerRecords<String,String> records = this.kafkaConsumer.poll(Duration.ofMillis(300));
                records.forEach(t->{
                    count.incrementAndGet();
                    System.out.println("数量：" + count.get()+ " ：" + t.toString());
                });
            }
        }catch (Exception e){
            e.printStackTrace();
            kafkaConsumer.close();
        }
    }
}
