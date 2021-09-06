package cpm.hdu.requestlog.source;

import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;

import java.util.Properties;

/**
 * @ClassName：KafkaTableSource
 * @Description：TODO
 * @Author：ssf
 * @Date：2021/3/18 11:32 上午
 * @Versiion：1.0
 */
public class KafkaTableSource {
    private static String kafkaHost = "172.31.196.186";
    public static FlinkKafkaConsumer kafkaTableSource(){

        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers",kafkaHost + ":9092," + kafkaHost + ":9093," + kafkaHost + ":9094");
        //  properties.setProperty("bootstrap.servers", "192.168.31.203:9092,192.168.31.203:9093,192.168.31.203:9094")
        properties.setProperty("group.id", "test");
        //  properties.setProperty("enable.auto.commit","false")
        //  properties.setProperty("auto-offset-reset","earliest")
        String topic = "TestComposeTopic";
        return new FlinkKafkaConsumer<>(topic, new StudentActivitySchema(), properties);
    }

}