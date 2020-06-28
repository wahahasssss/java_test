package com.hdu.chapter13_spark.structured_stream

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.StructType

/**
  * DESCRIPTION:
  *
  * @author shushoufu
  * @Date 2019/5/20
  * @Time 7:18 PM
  */
case class Person(name:String,age:Int)

object ApplicationReadTextAndCsvFile {
  def main(args: Array[String]):Unit={
    val spark = SparkSession.builder()
      .appName("text_csv_socket")
      .master("local[4]")
      .getOrCreate();

    import spark.implicits._

    val socketDF = spark
      .readStream
      .format("socket")
      .option("host","localhost")
      .option("port",9999)
      .load()

    println(socketDF.isStreaming)

    socketDF.printSchema()


    val userSchema = new StructType().add("name","string").add("age","integer")

    val csvDF = spark
      .readStream
      .format("csv")
      .option("sep",";")
      .schema(userSchema)
      .load("scala_demo/src/main/scala/com/hdu/chapter13_spark/structured_stream/test.csv")

    val people = csvDF.as[Person]

    val result = csvDF.select("name").where("age>12")
    val names = result.as[String].map(s=>s);
//    println(s"select name from csvDF where age > 12; $result.")

    val query = names.writeStream
      .outputMode("append")
      .format("console")
      .start()

    query.awaitTermination()
  }
}
