package com.hdu.chapter13_spark.rdd

import org.apache.commons.lang.ArrayUtils
import org.apache.spark.{SparkConf, SparkContext}

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2019/5/8
 * @Time 10:57 AM
 */
object Application {

  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("RDDApplication").setMaster("local");
    val sparkContext = new SparkContext(sparkConf)
    val data = Array(1, 2, 3, 4, 5)
    val distData = sparkContext.parallelize(data)
    println(distData.count())

    val distFile = sparkContext.textFile("scala_demo/src/main/resources/test.txt")
    distFile.foreach(s => println(s))


    val lines = sparkContext.textFile("scala_demo/src/main/resources/test.txt")

    val length = lines.map(s => s.length)

    val totalLength = length.reduce((a, b) => (a + b))

    println(s"length list is " + ArrayUtils.toString(length.collect(), ",") + s",total length is $totalLength")


    val pairs = lines.map(s => (s, 1))
    val counts = pairs.reduceByKey((a, b) => (a + b))
    counts.foreach(s => println(s))


  }

}
