package com.hdu.producer;

import com.alibaba.fastjson.JSON;
import com.hdu.model.StudentsActivityInfo;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.Random;
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
    private volatile KafkaProducer<String, String> kafkaProducer;
    //    private String topic = "test.waimai_lupin_parser";
    private String topic = "TestComposeTopic";
    private AtomicInteger count = new AtomicInteger(0);

    public KafkaProducerService() {
        Properties props = new Properties();
        String ip = "172.31.196.186";
        props.put("bootstrap.servers", String.format("%s:9092,%s:9093,%s:9094", ip, ip, ip)); // 这是 docker容器对应
//        props.put("bootstrap.servers","192.168.31.203:9092,192.168.31.203:9093,192.168.31.203:9094"); // 这是 docker容器对应
        props.put("acks", "1");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("compression.type", "gzip");
        props.put("retries", 3);
        props.put("timeout.ms", 10000);
        props.put("acks", "1");
        props.put("batch.size", 163840);
        props.put("linger.ms", 100);
        props.put("max.request.size", "2097152");
        props.put("advertised.host.name", ip);
        props.put("advertised.port", "9092");
        kafkaProducer = new KafkaProducer<>(props);
    }


    public void sendBatchMessage(int batch) {
        for (int i = 0; i < batch; i++) {
            StudentsActivityInfo s = new StudentsActivityInfo();
            s.setActivityTimestamp(System.currentTimeMillis());
            s.setActivityTime("avg");
            s.setAge(i);
            s.setName("shu");
            s.setBirthday("");
            if (i % 2 == 0) {
                s.setName("零");
                s.setAge(0);
            } else {
                s.setName("一");
            }
            kafkaProducer.send(new ProducerRecord<>(topic, s.getActivityTimestamp().toString(), JSON.toJSONString(s)), new ProducerCallBck());
            count.incrementAndGet();
            System.out.println("producer message 1111" + count.get() + ":" + JSON.toJSONString(s));
        }
    }

    public void sendBatchStudentActivity(int batch) {
        for (int i = 0; i < batch; i++) {
            StudentsActivityInfo studentsActivityInfo = StudentsActivityInfo.randomInit();
            kafkaProducer.send(new ProducerRecord<>(topic, 1, String.valueOf(i), JSON.toJSONString(studentsActivityInfo)), new ProducerCallBck());
        }
    }

    public void sendBatchStudentActivityForever() throws InterruptedException {
        int batch = 10;
        int nextDuration = 1000;
        while (true) {
            for (int i = 0; i < batch; i++) {
                StudentsActivityInfo studentsActivityInfo = StudentsActivityInfo.randomInit();
                kafkaProducer.send(new ProducerRecord<>(topic, 1, String.valueOf(i), JSON.toJSONString(studentsActivityInfo)), new ProducerCallBck());
            }
            Thread.sleep(nextDuration);
            batch = new Random().nextInt(100);
            nextDuration = new Random().nextInt(1000) + 1000;
            System.out.println("send batch msg " + batch);
        }
    }

    class ProducerCallBck implements Callback {
        @Override
        public void onCompletion(RecordMetadata recordMetadata, Exception e) {
            if (e != null) {
                e.printStackTrace();
            }
        }
    }
}
