package com.hdu.chapter14_flink.datastream

import org.apache.flink.streaming.api.functions.sink.SinkFunction
import org.apache.flink.walkthrough.common.sink.AlertSink
import org.slf4j.{Logger, LoggerFactory}

/**
 * @author shushoufu
 * @date 2020/08/14
 **/
object MyAlertSink {

}

class MyAlertSink extends SinkFunction[MyAlert] {
  val LOG: Logger = LoggerFactory.getLogger(classOf[AlertSink])

  override def invoke(value: MyAlert, context: SinkFunction.Context[_]): Unit = {
    LOG.info("this is my alert , {}", value)
  }
}
