package com.hdu.requestlog.sink

import org.apache.flink.streaming.api.functions.sink.{RichSinkFunction, SinkFunction}
import org.apache.flink.types.Row

class PrintSinkWithAny extends RichSinkFunction[Any]{
  override def invoke(value: Any, context: SinkFunction.Context[_]): Unit = {
    print("print sink with " + value)
  }
}
