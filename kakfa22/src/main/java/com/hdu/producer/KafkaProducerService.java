package com.hdu.producer;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2019/4/30
 * @Time 3:57 PM
 */
public class KafkaProducerService {
//    private static Logger LOG = LoggerFactory.getLogger(KafkaProducerService.class);
    private volatile KafkaProducer<String,String> kafkaProducer;
    private String topic = "test.waimai_lupin_parser";
    private AtomicInteger count = new AtomicInteger(0);

    public KafkaProducerService() {
        Properties props = new Properties();
        props.put("bootstrap.servers","47.94.245.160:9093,47.94.245.160:9092,47.94.245.160:9094");
        props.put("acks","1");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("compression.type","gzip");
        props.put("retries",3);
        props.put("timeout.ms",10000);
        props.put("acks","1");
        props.put("batch.size",163840);
        props.put("linger.ms",100);
        props.put("max.request.size","2097152");
        kafkaProducer = new KafkaProducer<>(props);
    }


    public void sendBatchMessage(int batch){
        for (int i = 0;i < batch;i++){
            kafkaProducer.send(new ProducerRecord<>(topic,1,String.valueOf(i), String.valueOf(System.currentTimeMillis())),new ProducerCallBck());
        }
    }

    class ProducerCallBck implements Callback{
        @Override
        public void onCompletion(RecordMetadata recordMetadata, Exception e) {
            if (e!=null){
                e.printStackTrace();
            }else {
                System.out.println("send message " + recordMetadata.topic() + ", count is " + count.incrementAndGet());
            }
        }
    }
}
