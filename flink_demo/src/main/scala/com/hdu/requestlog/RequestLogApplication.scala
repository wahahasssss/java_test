package com.hdu.requestlog

import com.hdu.chapter14_flink.datastream.PrintJob
import com.hdu.requestlog.job.{KafkaConsumerJob, KafkaConsumerTableJob, RequestLogJob}
import org.slf4j.LoggerFactory


/**
 * @author shushoufu
 * @date 2020/08/26
 **/
object RequestLogApplication {
  final val logger = LoggerFactory.getLogger(this.getClass)

  @throws[Exception]
  def main(args: Array[String]): Unit = {
    logger.info("begin stream handle");
    //    PrintJob.start()
    //    KafkaConsumerJob.start
    KafkaConsumerTableJob.start()
    logger.info("end stream handle\"")
  }
}
