package com.hdu.chapter3_source

import scala.io.Source

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/7/11
 * @Time 下午7:54
 */

class SourceHandle {
  var tmp = 0;

  def handle(b: Int): Unit = {
    tmp = b;
    println(tmp)
  }
}

object SourceHandle extends App {
  if (args.length >= 0) {
    for (line <- Source.fromFile("/Users/shushoufu/Desktop/document/java_test/scala_demo/src/main/resources/test.txt").getLines()) {
      println(line.length + " " + line);
    }
  }

  val sourceHandle = new SourceHandle()
  sourceHandle.handle(1111111111);
}


