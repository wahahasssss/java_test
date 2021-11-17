package com.hdu.chapter13_spark.structured_stream

import org.apache.spark.sql.SparkSession

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2019/5/10
 * @Time 11:24 AM
 */
object Application {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder()
      .appName("StructuredNetworkWordCount")
      .master("local")
      .getOrCreate()
    import spark.implicits._
    val lines = spark.readStream
      .format("socket")
      .option("host", "localhost")
      .option("port", 9999)
      .load()

    val words = lines.as[String].flatMap(_.split(" "))
    val wordCounts = words.groupBy("value").count()

    val query = wordCounts.writeStream
      .outputMode("complete")
      .format("console")
      .start()

    query.awaitTermination()

  }
}
