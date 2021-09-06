package com.hdu.requestlog.detecotors

import com.hdu.requestlog.model.StudentsActivityInfo
import com.hdu.requestlog.utils.JsonUtil
import org.apache.flink.streaming.api.functions.KeyedProcessFunction
import org.apache.flink.util.Collector
import org.apache.flink.walkthrough.common.entity.{Alert, Transaction}

/**
 * @author shushoufu
 * @date 2020/10/19
 **/
class KafkaDetector extends KeyedProcessFunction[String, StudentsActivityInfo, String]{
  override def processElement(i: StudentsActivityInfo, context: KeyedProcessFunction[String, StudentsActivityInfo, String]#Context, collector: Collector[String]): Unit = {
    collector.collect(JsonUtil.toJsonString(i))
  }
}
