package com.hdu.requestlog.utils

import java.util.Properties

/**
 * @author shushoufu
 * @date 2020/08/27
 **/
object PropertiesUtils {
  def loadProperties(): Properties = {
    val properties = new Properties();
    val in = this.getClass.getClassLoader.getResourceAsStream("application.properties")
    if (null == in) {
      throw new Exception("无法读取配置文件信息")
    }
    properties.load(in)
    properties
  }
}
