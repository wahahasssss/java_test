//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by CTWLPC on 2017/7/4.
// */
//public class JavaKafkaSimpleConsumerAPITest {
//    public static void main(String[] args) {
//        JavaKafkaSimpleConsumerAPI example = new JavaKafkaSimpleConsumerAPI();
//        long maxReads = 1000000;
//        String topic = "test";
//        int partitionID = 2;
//
//        KafkaTopicPartitionInfo topicPartitionInfo = new KafkaTopicPartitionInfo(topic, partitionID);
//        List<KafkaBrokerInfo> seeds = new ArrayList<KafkaBrokerInfo>();
//        seeds.add(new KafkaBrokerInfo("192.168.101.81", 9092));
//        seeds.add(new KafkaBrokerInfo("192.168.101.82", 9092));
//        seeds.add(new KafkaBrokerInfo("192.168.101.80",9092));
//
//        try {
//            example.run(maxReads, topicPartitionInfo, seeds);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        // 获取该topic所属的所有分区ID列表
//        System.out.println(example.fetchTopicPartitionIDs(seeds, topic, 100000, 64 * 1024, "client-id"));
//    }
//}
