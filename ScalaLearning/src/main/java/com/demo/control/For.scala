package com.demo.control

import java.io.File

import org.junit.Test

/**
  * Created by a549238 on 1/7/14.
  */
class For {

  @Test
  def testFor() = {
    for (i <- 1.to(10)
         if log(i)
         if i % 2 == 0)
      println("Counting " + i)
  }

  def log(message: Int): Boolean = {
    println("Evaluating " + message)
    true;
  }

  def listFile(files: Array[File]): Unit = {
    files.foreach((file) => {
      if (file.isDirectory)
        listFile(file.listFiles())
      else if (file.getName.endsWith(".class"))
        println("Found " + file.getName)
    })

  }

  @Test
  def testListFile() = {
    listFile(Array(new File(".")))
  }

  @Test
  def testListMethodName() = {
    val parttern: String = ".*def.*"
    val file: File = new File(".")
    listMethodName(parttern, file)
  }

  def listMethodName(parttern: String, file: File): Unit = {

    if (file.isDirectory)
      for (subFile <- file.listFiles();
           if (subFile.getName.endsWith(".java") || subFile.getName.endsWith(".scala"));
           line <- scala.io.Source.fromFile(subFile).getLines();
           if (line.trim.matches(parttern)))
        println(subFile.getName + "->" + line)
    else {
      scala.io.Source.fromFile(file).getLines().foreach((line) => {
        if (line.trim.matches(parttern))
          println(file.getName + "->" + line)
      })
    }

  }

  // @Test
  def testValue() = {
    val userNames = Array("Ted Neward", "Neal Ford", "Scott Davis",
      "Venkat Subramaniam", "David Geary")

    for (name <- userNames;
         firstName = name.substring(0, name.indexOf(" "));
         if (firstName.length > 4))
      println("Found first Name:" + firstName)
  }
}
