//import junit.framework.TestCase;
//
///**
// * Created by CTWLPC on 2017/7/4.
// */
//public class KafkaConsumerHighApiTest extends TestCase {
//    public static void main(String[] args) {
//        String zookeeper = "192.168.101.83:2181";
//        String groupId = "group1";
//        String topic = "test";
//        int threads = 1;
//
//        KafkaConsumerHighApi example = new KafkaConsumerHighApi(topic, threads, zookeeper, groupId);
//        new Thread(example).start();
//
//        // 执行10秒后结束
//        int sleepMillis = 600000;
//        try {
//            Thread.sleep(sleepMillis);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        // 关闭
//        example.shutdown();
//    }
//}