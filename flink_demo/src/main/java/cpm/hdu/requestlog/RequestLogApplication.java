package cpm.hdu.requestlog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName：RequestLogApplication
 * @Description：TODO
 * @Author：ssf
 * @Date：2021/3/18 11:30 上午
 * @Versiion：1.0
 */

public class RequestLogApplication {
    private static Logger logger = LoggerFactory.getLogger(RequestLogApplication.class);
    public static void main(String[] args){
        logger.info("begin stream handle");
//    PrintJob.start()
//    KafkaConsumerJob.start
//        KafkaConsumerTableJob.start();
        logger.info("end stream handle\"");
    }
}