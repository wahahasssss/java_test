package com.hdu.requestlog.exception

/**
 * @author shushoufu
 * @date 2020/08/26
 **/
class EsConfigException extends RuntimeException {
  override def getMessage: String = super.getMessage

  override def getLocalizedMessage: String = super.getLocalizedMessage
}
