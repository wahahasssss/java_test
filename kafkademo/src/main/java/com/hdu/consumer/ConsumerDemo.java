package com.hdu.consumer;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.message.MessageAndMetadata;
import kafka.serializer.Decoder;
import kafka.serializer.StringDecoder;
import kafka.utils.VerifiableProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2019/3/21
 * @Time 3:38 PM
 */
public class ConsumerDemo {
    private static Logger LOG = LoggerFactory.getLogger(ConsumerDemo.class);
    private ConsumerConnector consumer;
    private String topic;
    private final int DEFAULT_CONSUME_THREAD_NUM = 1;
    private Map<String, List<KafkaStream<String, String>>> consumerMap;
    private volatile AtomicInteger count = new AtomicInteger(0);

    public ConsumerDemo() {
        topic = "test.waimai_lupin_parser";
        Properties kafkaProperties = new Properties();
        kafkaProperties.put("zookeeper.connect", "www.boboc.club:2181,www.boboc.club:2181");
        kafkaProperties.put("zookeeper.session.timeout.ms", "60000");
        kafkaProperties.put("zookeeper.sync.time.ms", "2000");
        kafkaProperties.put("group.id", "test2");
        kafkaProperties.put("fetch.message.max.bytes", "20971520");
        kafkaProperties.put("auto.commit.interval.ms", "2000");
        kafkaProperties.put("rebalance.max.retries", "5");
        kafkaProperties.put("rebalance.backoff.ms", "12000");
        kafkaProperties.put("auto.offset.reset", "smallest");
//        kafkaProperties.put("auto.offset.reset","largest");
        consumer = Consumer.createJavaConsumerConnector(new ConsumerConfig(kafkaProperties));

        Map<String, Integer> topicCountMap = new HashMap();
        topicCountMap.put(topic, DEFAULT_CONSUME_THREAD_NUM);
        Decoder<String> keyDecoder = new StringDecoder(new VerifiableProperties());
        Decoder<String> valueDecoder = new StringDecoder(new VerifiableProperties());
        consumerMap = consumer.createMessageStreams(topicCountMap, keyDecoder, valueDecoder);
    }


    public void run() {
        Thread consumerThread = new Thread(() -> {
            List<KafkaStream<String, String>> streams = consumerMap.get(topic);
            if (streams != null && streams.size() > 0) {
                KafkaStream kafkaStream = streams.get(0);
                ConsumerIterator<String, String> consumerIterator = kafkaStream.iterator();
                try {
                    while (consumerIterator.hasNext()) {
                        MessageAndMetadata<String, String> messageAndMetadata = consumerIterator.next();
                        System.out.println(String.format("count is  %d，partition is %s", count.incrementAndGet(), messageAndMetadata.partition()));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        consumerThread.start();

    }

    private void close() {
        consumer.shutdown();
    }

    public static void main(String[] args) {
        ConsumerDemo consumerDemo = new ConsumerDemo();
        consumerDemo.run();
    }
}
