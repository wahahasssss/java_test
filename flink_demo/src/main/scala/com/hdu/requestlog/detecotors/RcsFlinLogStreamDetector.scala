package com.hdu.requestlog.detecotors

import scala.collection.JavaConversions._
import java.util.{ArrayList, Collections, HashMap, List}

import com.fasterxml.jackson.core.`type`.TypeReference
import org.apache.flink.streaming.api.functions.KeyedProcessFunction
import org.apache.flink.util.Collector
import org.slf4j.LoggerFactory
import com.hdu.requestlog.model.{Body, EntityMapTo, RcsRequestLogTo, RequestLogParamsTo, RequestLogResultTo}
import com.hdu.requestlog.utils.JsonUtil

import scala.collection.immutable

/**
 * @author shushoufu
 * @date 2020/08/27
 **/
class RcsFlinLogStreamDetector extends KeyedProcessFunction[String, String, RcsRequestLogTo] {
  private final val logger = LoggerFactory.getLogger(this.getClass)

  override def processElement(s: String, context: KeyedProcessFunction[String, String, RcsRequestLogTo]#Context, collector: Collector[RcsRequestLogTo]): Unit = {
    try {
      val results: HashMap[String, AnyRef] = JsonUtil.string2Obj(s, classOf[HashMap[String, AnyRef]]).get
      val requestLog: RcsRequestLogTo = RcsRequestLogTo(
        requestTime = (if ("null" == String.valueOf(results.getOrDefault("requestTime", ""))) "" else String.valueOf(results.getOrDefault("requestTime", ""))),
        requestId = if ("null" == String.valueOf(results.getOrDefault("requestId", ""))) "" else String.valueOf(results.getOrDefault("requestId", "")),
        method = if ("null" == String.valueOf(results.getOrDefault("methodName", ""))) "" else String.valueOf(results.getOrDefault("methodName", "")),
        accessKey = if ("null" == String.valueOf(results.getOrDefault("accessKey", ""))) "" else String.valueOf(results.getOrDefault("accessKey", "")),
        times = 0,
        params = null,
        results = null
      )
      requestLog.setTimes(results.getOrDefault("responseTime", "0").toString.toLong - requestLog.requestTime.toLong)
      handleInputParams(results, requestLog)
      handleResults(results, requestLog)
      collector.collect(requestLog)
    } catch {
      case e: Exception =>
        logger.error("RcsFlinStreamDetector processElement failed, ", e)
    }
  }

  private def handleInputParams(results: HashMap[String, AnyRef], requestLog: RcsRequestLogTo): Unit = {
    if (results != null && results.containsKey("inputParam")) {
      val inputParams: String = String.valueOf(results.get("inputParam"))
      val inputParamsMaps: List[HashMap[String, AnyRef]] = JsonUtil.string2Obj(inputParams, new TypeReference[List[HashMap[String, AnyRef]]]() {}).get
      if (inputParamsMaps == null || inputParamsMaps.size == 0) {
        return
      }
      val inputParamsMap: HashMap[String, AnyRef] = inputParamsMaps.get(0)
      val params: RequestLogParamsTo = RequestLogParamsTo(
        appKey = if ("null" == String.valueOf(inputParamsMap.getOrDefault("app_key", ""))) "" else String.valueOf(inputParamsMap.getOrDefault("app_key", "")),
        businessExt = if ("null" == String.valueOf(inputParamsMap.getOrDefault("busi_ext", ""))) "" else String.valueOf(inputParamsMap.getOrDefault("busi_ext", "")),
        deviceExt = if ("null" == String.valueOf(inputParamsMap.getOrDefault("device_ext", ""))) "" else String.valueOf(inputParamsMap.getOrDefault("device_ext", "")),
        eventId = if ("null" == String.valueOf(inputParamsMap.getOrDefault("event_id", ""))) "" else String.valueOf(inputParamsMap.getOrDefault("event_id", "")),
        terminalId = if ("null" == String.valueOf(inputParamsMap.getOrDefault("terminal_id", ""))) "" else String.valueOf(inputParamsMap.getOrDefault("terminal_id", "")),
        requestTime = 0L,
        entityType = if ("null" == String.valueOf(inputParamsMap.getOrDefault("entityType", ""))) "" else String.valueOf(inputParamsMap.getOrDefault("entityType", "")),
        entityValue = if ("null" == String.valueOf(inputParamsMap.getOrDefault("entityValue", ""))) "" else String.valueOf(inputParamsMap.getOrDefault("entityValue", "")),
        entityMap = null,
        sceneKey = ""
      )

      val requestTime: String = String.valueOf(inputParamsMap.getOrDefault("request_time", "0"))
      if (inputParams != null && inputParamsMap.containsKey("request_time")) {
        try {
          params.setRequestTime(requestTime.toLong)
        } catch {
          case e: Exception =>
          //            logger.error("解析入参request_time失败, 【{}】", requestTime, e)
        }
      }

      if (inputParamsMap != null && inputParamsMap.containsKey("entity_map")) {
        val entityMap: immutable.HashMap[String, AnyRef] = inputParamsMap.getOrDefault("entity_map", Collections.emptyMap).asInstanceOf[immutable.HashMap[String, AnyRef]]
        val entityMapTo: EntityMapTo = EntityMapTo(
          address = if ("null" == String.valueOf(entityMap.getOrDefault("address", ""))) "" else String.valueOf(entityMap.getOrDefault("address", "")),
          companyId = if ("null" == String.valueOf(entityMap.getOrDefault("company_id", ""))) "" else String.valueOf(entityMap.getOrDefault("company_id", "")),
          deviceId = if ("null" == String.valueOf(entityMap.getOrDefault("device_id", ""))) "" else String.valueOf(entityMap.getOrDefault("device_id", "")),
          ip = if ("null" == String.valueOf(entityMap.getOrDefault("ip", ""))) "" else String.valueOf(entityMap.getOrDefault("ip", "")),
          openId = if ("null" == String.valueOf(entityMap.getOrDefault("open_id", ""))) "" else String.valueOf(entityMap.getOrDefault("open_id", "")),
          passportId = if ("null" == String.valueOf(entityMap.getOrDefault("passport_id", ""))) "" else String.valueOf(entityMap.getOrDefault("passport_id", "")),
          phone = if ("null" == String.valueOf(entityMap.getOrDefault("phone", ""))) "" else String.valueOf(entityMap.getOrDefault("phone", ""))
        )
        params.setEntityMap(entityMapTo)
      } else {
        val entityMapTo: EntityMapTo = EntityMapTo("", "", "", "", "", "", "")
        params.setEntityMap(entityMapTo)
      }
      params.setSceneKey("")
      if (inputParamsMap != null && inputParamsMap.containsKey("sceneKey")) {
        params.setSceneKey(String.valueOf(inputParamsMap.get("sceneKey")))
      }
      if (inputParamsMap != null && inputParamsMap.containsKey("scene_key")) {
        params.setSceneKey(String.valueOf(inputParamsMap.get("scene_key")))
      }
      requestLog.setParams(params)
    }
  }

  private def handleResults(results: HashMap[String, AnyRef], requestLog: RcsRequestLogTo): Unit = {
    if (results != null && results.containsKey("outParam")) {
      val responseStr: String = String.valueOf(results.get("outParam"))
      val responseMap: HashMap[String, AnyRef] = JsonUtil.string2Obj(responseStr, classOf[HashMap[String, AnyRef]]).get
      if (responseMap == null) return
      val response: RequestLogResultTo = RequestLogResultTo(
        errorCode = if ("null" == String.valueOf(responseMap.getOrDefault("errorCode", ""))) "" else String.valueOf(responseMap.getOrDefault("errorCode", "")),
        message = if ("null" == String.valueOf(responseMap.getOrDefault("message", ""))) "" else String.valueOf(responseMap.getOrDefault("message", "")),
        success = if ("null" == String.valueOf(responseMap.getOrDefault("success", ""))) "" else String.valueOf(responseMap.getOrDefault("success", "")),
        body = null
      )

      if (responseMap != null && responseMap.containsKey("body")) {
        val bodyList: List[immutable.Map[String, AnyRef]] = new ArrayList[immutable.Map[String, AnyRef]]
        responseMap.get("body") match {
          case list: scala.collection.immutable.List[immutable.Map[String, AnyRef]] => for (l <- list) bodyList.add(l)
          case tmpMap: immutable.Map[String, AnyRef] => bodyList.add(tmpMap)
          case str: String => bodyList.add(immutable.Map("disposal_first" -> str))
          case _ => bodyList.add(immutable.Map("disposal_first" -> ""))
        }
        if (bodyList != null && bodyList.size > 0) {
          val bodies: List[Body] = new ArrayList[Body]
          for (bodyMap <- bodyList) {
            val body: Body = Body(
              disposalFirst = if ("null" == String.valueOf(bodyMap.getOrDefault("disposal_first", ""))) "" else String.valueOf(bodyMap.getOrDefault("disposal_first", "")),
              disposalSecond = if ("null" == String.valueOf(bodyMap.getOrDefault("disposal_second", ""))) "" else String.valueOf(bodyMap.getOrDefault("disposal_second", "")),
              result = if ("null" == String.valueOf(bodyMap.getOrDefault("result", ""))) "" else String.valueOf(bodyMap.getOrDefault("result", "")),
              message = if ("null" == String.valueOf(bodyMap.getOrDefault("msg", ""))) "" else String.valueOf(bodyMap.getOrDefault("msg", "")),
              identification = if ("null" == String.valueOf(bodyMap.getOrDefault("identification", ""))) "" else String.valueOf(bodyMap.getOrDefault("identification", "")),
              labelName = if ("null" == String.valueOf(bodyMap.getOrDefault("labelName", ""))) "" else String.valueOf(bodyMap.getOrDefault("labelName", "")),
              isHit = if ("null" == String.valueOf(bodyMap.getOrDefault("isHit", ""))) "" else String.valueOf(bodyMap.getOrDefault("isHit", ""))
            )
            bodies.add(body)
          }
          response.setBody(bodies)
        }
      }
      requestLog.setResults(response)
    }
  }
}
