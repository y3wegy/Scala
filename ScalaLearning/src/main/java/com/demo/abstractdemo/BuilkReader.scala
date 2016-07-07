package com.demo.abstractdemo

import java.io.{BufferedInputStream, File, FileInputStream}


/**
  * Created with IntelliJ IDEA.
  * User: a549238
  * Date: 2/27/13
  * Time: 3:15 PM
  * To change this template use File | Settings | File Templates.
  */
/*
抽象类test
 */
abstract class BuilkReader {
  type In
  val source: In

  def read: String

}

class StringBuilkReader(val source: String) extends BuilkReader {
  type In = String

  def read = source
}

class FileBuilkReader(val source: File) extends BuilkReader {
  type In = File

  def read = {
    val in = new BufferedInputStream(new FileInputStream(source))
    val numBytes = in.available()
    val bytes = new Array[Byte](numBytes)
    in.read(bytes, 0, numBytes)
    new String(bytes)
  }

}

object client {
  def main(args: Array[String]) {
    println(new StringBuilkReader("Hello Scala!").read)

    val path = Thread.currentThread().getContextClassLoader.getResource("")
    val projectpath = path.toString.substring(6, path.toString.indexOf("out")) //截取到项目根目录  ,同时去除“file:/”
    val filepath = projectpath + "src/com/demo/abstractdemo/BuilkReader.scala"

    println(path + "\n" + filepath + "\n" + projectpath)
    println(new FileBuilkReader(new File(filepath)).read)
  }
}

