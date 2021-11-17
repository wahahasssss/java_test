package com.hdu.requestlog.sink

import org.apache.flink.streaming.connectors.elasticsearch.{ActionRequestFailureHandler, RequestIndexer}
import org.elasticsearch.action.ActionRequest
import org.slf4j.LoggerFactory

/**
 * @author shushoufu
 * @date 2020/08/28
 **/
class EsFailureHandler extends ActionRequestFailureHandler {
  private final val logger = LoggerFactory.getLogger(this.getClass)

  @throws[Throwable]
  override def onFailure(actionRequest: ActionRequest, throwable: Throwable, i: Int, requestIndexer: RequestIndexer): Unit = {
    logger.info("es sink handle failed, " + actionRequest, throwable)
  }
}
