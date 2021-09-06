package com.hdu.requestlog.watermark

import java.time.Duration

import com.hdu.requestlog.model.StudentsActivityInfo
import org.apache.flink.api.common.eventtime.{TimestampAssignerSupplier, Watermark, WatermarkGenerator, WatermarkGeneratorSupplier, WatermarkOutput, WatermarkStrategy}

/**
 * @author shushoufu
 * @date 2020/11/03
 **/
class CustomWaterStrategy extends WatermarkStrategy[StudentsActivityInfo]{
  var maxTimestamp = 0L
  val delay = 3000
  override def createWatermarkGenerator(context: WatermarkGeneratorSupplier.Context): WatermarkGenerator[StudentsActivityInfo] = {
    val generator = new WatermarkGenerator[StudentsActivityInfo] {
      override def onEvent(t: StudentsActivityInfo, l: Long, watermarkOutput: WatermarkOutput): Unit = {
        maxTimestamp = Math.max(maxTimestamp, t.activityTimestamp)
      }

      override def onPeriodicEmit(watermarkOutput: WatermarkOutput): Unit = {
        watermarkOutput.emitWatermark(new Watermark(maxTimestamp - delay))
      }
    }
    generator
  }

}

object CustomWaterStrategy{

  def initWaterStrategy():CustomWaterStrategy={
    val waterStrategy = new CustomWaterStrategy;
    waterStrategy.withTimestampAssigner(new CustomTimestampAssignerSupplier)
    waterStrategy.withIdleness(Duration.ofMinutes(1L))//超过多长时间没有消息，自动触发水印的策略
    waterStrategy
  }
}
