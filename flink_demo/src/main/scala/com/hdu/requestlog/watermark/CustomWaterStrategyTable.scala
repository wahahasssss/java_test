package com.hdu.requestlog.watermark

import java.time.Duration

import com.hdu.requestlog.job.StudentsActivity
import com.hdu.requestlog.model.StudentsActivityInfo
import org.apache.flink.api.common.eventtime._

/**
 * @author shushoufu
 * @date 2020/11/03
 **/
class CustomWaterStrategyTable extends WatermarkStrategy[StudentsActivity] {
  var maxTimestamp = 0L
  val delay = 3000

  override def createWatermarkGenerator(context: WatermarkGeneratorSupplier.Context): WatermarkGenerator[StudentsActivity] = {
    val generator = new WatermarkGenerator[StudentsActivity] {
      override def onEvent(t: StudentsActivity, l: Long, watermarkOutput: WatermarkOutput): Unit = {
        maxTimestamp = Math.max(maxTimestamp, t.activityTimestamp)
      }

      override def onPeriodicEmit(watermarkOutput: WatermarkOutput): Unit = {
        watermarkOutput.emitWatermark(new Watermark(maxTimestamp - delay))
      }
    }
    generator
  }

}

object CustomWaterStrategyTable {

  def initWaterStrategy(): CustomWaterStrategyTable = {
    val waterStrategy = new CustomWaterStrategyTable;
    waterStrategy.withTimestampAssigner(new CustomTimestampAssignerSupplierTable)
    waterStrategy.withIdleness(Duration.ofMinutes(1L)) //超过多长时间没有消息，自动触发水印的策略
    waterStrategy
  }
}


