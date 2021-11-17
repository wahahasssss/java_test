package com.hdu.requestlog.sink

import com.hdu.requestlog.utils.JsonUtil
import org.apache.flink.streaming.api.functions.sink.SinkFunction

/**
 * @author shushoufu
 * @date 2020/11/03
 **/
case class PrintSinkWithType[T](val sinkId: String) extends SinkFunction[T] {
  override def invoke(value: T, context: SinkFunction.Context[_]): Unit = {
    print("\nPrintSinkWithType " + sinkId + ":" + JsonUtil.toJsonString(value))
  }
}

