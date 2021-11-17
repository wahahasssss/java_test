package com.hdu.sparkapi.spark

import java.util.Properties

import org.apache.spark.api.java.JavaRDD
import org.apache.spark.sql.types.{DataType, DataTypes, StructField}
import org.apache.spark.sql.{Row, RowFactory, SQLContext, SparkSession}
import org.apache.spark.{SparkConf, SparkContext, sql}

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/11/19
 * @Time 下午3:31
 */
object SimpleSparkApp {
  def main(args: Array[String]): Unit = {
    val logFile = "/projects/snappydata-1.0.2.1-bin/README.md"
    val conf = new SparkConf().setAppName("SimpleSparkApp").setMaster("spark://127.0.0.1:7077")
    val sc = new SparkContext(conf)
    val logData = sc.textFile(logFile)
    val numAs = logData.filter(line => line.contains("a")).count()
    val numBs = logData.filter(line => line.contains("b")).count()

    val wordCounts = logData.flatMap(line => line.split(" ")).map(word => (word, 1)).reduceByKey((a, b) => a + b)

    println(s"Lines with a :$numAs,Lines with b:$numBs,WordCount results:$wordCounts")

    wordCounts.saveAsTextFile("/projects/wcresults/wordcount.txt")


    val wordCountData = sc.parallelize(wordCounts.collect());

    sc.stop()

  }
}
