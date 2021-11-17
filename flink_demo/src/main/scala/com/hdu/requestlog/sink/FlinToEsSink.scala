package com.hdu.requestlog.sink

import java.text.SimpleDateFormat
import java.util.{ArrayList, Date, HashMap, List, Properties}

import scala.collection.JavaConversions._
import com.hdu.requestlog.model.RcsRequestLogTo
import com.hdu.requestlog.utils.{JsonUtil, PropertiesUtils}
import org.apache.commons.lang.{SerializationUtils, StringUtils}
import org.apache.flink.api.common.functions.RuntimeContext
import org.apache.flink.streaming.connectors.elasticsearch.{ElasticsearchSinkFunction, RequestIndexer}
import org.elasticsearch.action.index.IndexRequest
import org.elasticsearch.client.Requests
import org.elasticsearch.common.xcontent.XContentType
import org.slf4j.LoggerFactory

/**
 * @author shushoufu
 * @date 2020/08/27
 **/
case class FlinToEsSink(private val indexName: String, private val flatIndexName: String, private val indexType: String) extends ElasticsearchSinkFunction[RcsRequestLogTo] {
  private final val simpleDateFormat = new SimpleDateFormat("yyyyMMdd")
  private final val logger = LoggerFactory.getLogger(this.getClass)

  private[this] def createIndex(element: RcsRequestLogTo): Array[IndexRequest] = {
    val json = JsonUtil.toJsonString(element)
    val indexRequests: List[IndexRequest] = new ArrayList[IndexRequest]
    indexRequests.add(Requests.indexRequest.index(indexName).`type`(indexType).id(element.requestId).source(json, XContentType.JSON))
    var dt: String = simpleDateFormat.format(new Date)
    if (StringUtils.isNotEmpty(element.requestTime)) dt = simpleDateFormat.format(new Date(element.requestTime.toLong))
    val elementMap: HashMap[String, String] = new HashMap[String, String]
    elementMap.put("dt", dt)
    elementMap.put("request_time", element.requestTime)
    elementMap.put("request_id", element.requestId)
    elementMap.put("method", element.method)
    elementMap.put("params_scene_key", element.params.sceneKey)
    elementMap.put("params_entity_type", element.params.entityType)
    elementMap.put("params_entity_value", element.params.entityValue)
    elementMap.put("params_app_key", element.params.appKey)
    elementMap.put("params_event_id", element.params.eventId)
    elementMap.put("params_terminal_id", element.params.terminalId)
    elementMap.put("params_entity_map_ip", if (element.params.entityMap == null) "" else element.params.entityMap.ip)
    elementMap.put("params_entity_map_company_id", if (element.params.entityMap == null) "" else element.params.entityMap.companyId)
    elementMap.put("params_entity_map_passport_id", if (element.params.entityMap == null) "" else element.params.entityMap.passportId)
    elementMap.put("params_entity_map_device_id", if (element.params.entityMap == null) "" else element.params.entityMap.deviceId)
    elementMap.put("params_entity_map_phone", if (element.params.entityMap == null) "" else element.params.entityMap.phone)
    elementMap.put("params_entity_map_address", if (element.params.entityMap == null) "" else element.params.entityMap.address)
    elementMap.put("params_entity_map_open_id", if (element.params.entityMap == null) "" else element.params.entityMap.openId)
    elementMap.put("params_device_ext", element.params.deviceExt)
    elementMap.put("params_busi_ext", element.params.businessExt)
    elementMap.put("params_request_time", element.params.requestTime.toString)
    elementMap.put("access_key", element.accessKey)
    elementMap.put("times", element.times.toString)
    elementMap.put("results_success", element.results.success)
    elementMap.put("results_error_code", element.results.errorCode)
    elementMap.put("results_message", element.results.message)
    if (element.results.body != null && element.results.body.size > 0) {
      val copyMap: HashMap[String, String] = SerializationUtils.clone(elementMap).asInstanceOf[HashMap[String, String]]
      for (body <- element.results.body) {
        copyMap.put("results_body_identification", body.identification)
        copyMap.put("results_body_label_name", body.labelName)
        copyMap.put("results_body_isHit", body.isHit)
        copyMap.put("results_body_result", body.result)
        copyMap.put("results_body_disposal_first", body.disposalFirst)
        copyMap.put("results_body_disposal_second", body.disposalSecond)
        copyMap.put("results_body_msg", body.message)
        var baseId: String = element.requestId
        if (StringUtils.isNotEmpty(body.identification)) {
          baseId = baseId + body.identification
        } else if (StringUtils.isNotEmpty(body.disposalFirst)) {
          baseId = baseId + body.disposalFirst
        }
        indexRequests.add(Requests.indexRequest.index(flatIndexName).`type`(indexType).id(baseId).source(copyMap))
      }
    }
    else {
      elementMap.put("results_body_identification", "")
      elementMap.put("results_body_label_name", "")
      elementMap.put("results_body_isHit", "")
      elementMap.put("results_body_result", "")
      elementMap.put("results_body_disposal_first", "")
      elementMap.put("results_body_disposal_second", "")
      elementMap.put("results_body_msg", "")
      indexRequests.add(Requests.indexRequest.index(flatIndexName).`type`(indexType).id(element.requestId).source(elementMap))
    }

    val results: Array[IndexRequest] = new Array[IndexRequest](indexRequests.size)
    indexRequests.toArray(results)
    results
  }

  override def process(t: RcsRequestLogTo, runtimeContext: RuntimeContext, requestIndexer: RequestIndexer): Unit = {
    requestIndexer.add(createIndex(t): _*)
    //    logger.info("flin to es , index is {}, base id is {}", indexName, t.requestId)
  }

}

object FlinToEsSink {
  private val properties: Properties = PropertiesUtils.loadProperties()
  private val indexName = properties.getProperty("es.request.log.index")
  private val flatIndexName = properties.getProperty("es.request.flat.log.index")
  private val indexType = properties.getProperty("es.request.log.index.type")
  private val flinToEsSink = FlinToEsSink(indexName, flatIndexName, indexType)

  /**
   * 获取es sink
   *
   * @return
   */
  def getEsSinkInstance(): FlinToEsSink = {
    flinToEsSink
  }
}
