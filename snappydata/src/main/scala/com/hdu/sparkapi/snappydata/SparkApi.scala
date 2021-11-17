package com.hdu.sparkapi.snappydata

import org.apache.spark.sql.{Row, SnappySession, SparkSession}

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/11/13
 * @Time 上午11:15
 */


case class Data(COL1: Int, COL2: Int, COL3: Int)

object SparkApi extends App {
  print("start =====")
  val sparkSession = SparkSession.builder()
    .master("local")
    .appName("word count")
    .config("option", "123")
    .getOrCreate();
  val snappy = new SnappySession(sparkSession.sparkContext);


  val props = Map("BUCKETS" -> "8")
  val data = Seq(Seq(1, 2, 3), Seq(7, 8, 9), Seq(9, 2, 3), Seq(4, 2, 3), Seq(5, 6, 7))
  val rdd = sparkSession.sparkContext.parallelize(data, data.length)
    .map(s => new Data(s(0), s(1), s(2)))

  val df = snappy.createDataFrame(rdd);
  snappy.dropTable("COLUMN_TABLE", ifExists = true)
  snappy.createTable("COLUMN_TABLE", "column", df.schema, props)

  df.write.insertInto("COLUMN_TABLE")

  val results = snappy.sql("SELECT * FROM COLUMN_TABLE")

  println("contents of column table are:")
  results.foreach(r => println(r))


  /*##################分割线##########*/


  snappy.dropTable("ROW_TABLE", ifExists = true)
  val props2 = Map.empty[String, String]
  snappy.createTable("ROW_TABLE", "row", df.schema, props2)

  df.write.insertInto("ROW_TABLE")
  val results2 = snappy.sql("SELECT * FROM ROW_TABLE")
  println("contents of row table are:")

  results2.foreach(r => println(r))


  snappy.update("ROW_TABLE", "COL3 = 3", Row(99), "COL3")

  val result3 = snappy.sql("SELECT * FROM ROW_TABLE")
  println("contents of row table are after setting col3 = 99 are:")
  results.foreach(r => println(r))

  snappy.sql("UPDATE ROW_TABLE SET COL1 = 100 WHERE COL3 = 99")

  val result4 = snappy.sql("SELECT * FROM ROW_TABLE")
  println("contents of row table are after setting col1 = 100 are:")
  result4.foreach(r => println(r))
}

class SparkApi {

}

