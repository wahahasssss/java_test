package com.hdu.chapter13_spark.dataframe_and_datasets

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.StructType

/**
  * DESCRIPTION:
  *
  * @author shushoufu
  * @Date 2019/5/13
  * @Time 3:43 PM
  */
object Application {
  def main(args:Array[String]):Unit={
    val spark: SparkSession = SparkSession
      .builder()
      .master("local")
      .appName("socket_source")
      .getOrCreate();

    val sockeDF = spark
      .readStream
      .format("socket")
      .option("host","127.0.0.1")
      .option("port","9999")
      .load()

    sockeDF.isStreaming

    sockeDF.printSchema()

    val userSchema = new StructType().add("name","string").add("age","integer")


    val csvDF = spark
      .readStream
      .option("sep",";")
      .schema(userSchema)
      .csv("scala_demo/src/main/scala/com/hdu/chapter13_spark/dataframe_and_datasets/test.csv") // Equivalent to format("csv").load("/path/to/directory")


  }
}
