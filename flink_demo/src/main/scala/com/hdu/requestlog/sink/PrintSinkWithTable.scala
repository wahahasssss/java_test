package com.hdu.requestlog.sink

import com.hdu.requestlog.job.StudentsActivity
import com.hdu.requestlog.model.StudentsActivityInfo
import org.apache.flink.api.java.tuple.Tuple2
import org.apache.flink.streaming.api.functions.sink.SinkFunction
import org.apache.flink.types.Row

class PrintSinkWithTable extends SinkFunction[(Boolean, StudentsActivity)] {
  override def invoke(value: (Boolean, StudentsActivity), context: SinkFunction.Context[_]): Unit = {
    print("row is : " + value)
  }
}
