package com.hdu.chapter13_spark.textfile

import org.apache.spark.sql.SparkSession


/**
  * DESCRIPTION:
  *
  * @author shushoufu
  * @Date 2019/5/7
  * @Time 5:09 PM
  */
object Applicaiton extends App {
  println("textfile demo start...")
  val logfile = "/usr/local/spark/spark-2.4.0-bin-hadoop2.7/README.md"
//  val logfile = "/README.md"
  val spark = SparkSession.builder().appName("SIMPLE APPLICATION").master("local").getOrCreate()
  val logData = spark.read.textFile(logfile).cache()
  val numAs = logData.filter(line=>line.contains("a")).count()
  val numBs = logData.filter(line=>line.contains("b")).count()
  println(s"Lines with a:$numAs,Lines with b:$numBs")
  spark.stop()
}
