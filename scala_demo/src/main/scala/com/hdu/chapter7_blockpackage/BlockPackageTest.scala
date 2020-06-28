package com.hdu.chapter7_blockpackage

import java.io.File

import scala.io.Source

/**
  * DESCRIPTION:
  *
  * @author shushoufu
  * @Date 2018/7/16
  * @Time 下午4:48
  */
object BlockPackageTest extends App {
  println("=============begin================")


  def processFile(fileName:String,width:Int)={
    def processLine(fileName:String,width:Int,line:String)={
      if(line.length>width){
        println(fileName + ":" + line.trim)
      }
    }

    val source = Source.fromFile(fileName)
    for(line<-source.getLines()){
      processLine(fileName,width,line)
    }
  }

  processFile("/Users/shushoufu/Desktop/document/java_test/scala_demo/src/main/resources/test.txt",10)
}
