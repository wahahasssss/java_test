package com.hdu.requestlog.sink

import org.apache.flink.streaming.api.functions.sink.SinkFunction

/**
 * @author shushoufu
 * @date 2020/10/19
 **/
class PrintSink extends SinkFunction[String] {
  override def invoke(value: String, context: SinkFunction.Context[_]): Unit = {
    print("print " + value + "\n")
  }
}
