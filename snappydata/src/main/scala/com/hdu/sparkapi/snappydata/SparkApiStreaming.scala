package com.hdu.sparkapi.snappydata

import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.apache.spark.sql.{SaveMode, SparkSession}
import org.apache.spark.streaming.{Duration, SnappyStreamingContext}

import scala.collection.mutable
/**
  * DESCRIPTION:
  *
  * @author shushoufu
  * @Date 2018/11/13
  * @Time 下午2:38
  */
case class ShowCaseSchemaStream(loc:Int,text:String)
object SparkApiStreaming extends App {
  val sparkSession = SparkSession.builder()
    .master("local")
    .appName("streaming")
    .getOrCreate();

  val snsc = new SnappyStreamingContext(sparkSession.sparkContext, Duration(1))
  val schema = StructType(List(StructField("id", IntegerType) ,StructField("text", StringType)))


  snsc.snappyContext.dropTable("streamingExample", ifExists = true)
  snsc.snappyContext.createTable("streamingExample", "column",  schema, Map.empty[String, String] , false)

  def rddList(start:Int, end:Int) = sparkSession.sparkContext.parallelize(start to end).map(i => ShowCaseSchemaStream( i, s"Text$i"))

  val dstream = snsc.queueStream[ShowCaseSchemaStream](
    mutable.Queue(rddList(1, 10), rddList(10, 20), rddList(20, 30)))

  val schemaDStream = snsc.createSchemaDStream(dstream )

  schemaDStream.foreachDataFrame(df => {
    df.write.format("column").
      mode(SaveMode.Append).
      options(Map.empty[String, String]).
      save()    })

  snsc.start()
  snsc.sql("select count(*) from streamingExample").show

}
class SparkApiStreaming(){
}

