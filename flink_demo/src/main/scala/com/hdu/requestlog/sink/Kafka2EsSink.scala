package com.hdu.requestlog.sink

import java.util.UUID

import com.hdu.requestlog.model.RcsRequestLogTo
import com.hdu.requestlog.utils.JsonUtil
import org.apache.flink.api.common.functions.RuntimeContext
import org.apache.flink.streaming.connectors.elasticsearch.{ElasticsearchSinkFunction, RequestIndexer}
import org.elasticsearch.client.Requests
import org.elasticsearch.common.xcontent.XContentType

/**
 * @author shushoufu
 * @date 2020/10/19
 **/
case class Kafka2EsSink(indexName:String, indexType:String) extends ElasticsearchSinkFunction[String]{

  override def process(t: String, runtimeContext: RuntimeContext, requestIndexer: RequestIndexer): Unit = {
    val mapData = {"content"->t}
    val jsonData = JsonUtil.toJsonString(mapData)
    requestIndexer.add(Requests.indexRequest.index(indexName).`type`(indexType).id(UUID.randomUUID().toString).source(jsonData, XContentType.JSON))
  }
}

object Kafka2EsSink{
  val index = "es_first_index";
  val indexType = "product"

  def initToEsSink:Kafka2EsSink={
    val sink = Kafka2EsSink(index, indexType)
    sink
  }
}
