package com.demo.begin

/**
  * Created by a549238 on 1/2/14.
  */
object TimerTest {
  def main(args: Array[String]): Unit = {
    oncePerSecond(timeFlies)
  }

  def oncePerSecond(callback: () => Unit): Unit = {
    while (true) {
      callback()
      Thread.sleep(1000)
    }
  }

  def timeFlies(): Unit = {
    Console.println("Time flies when you're having functionally...");
  }
}
