package com.demo.control

import org.junit.Test

/**
  * Created by a549238 on 1/7/14.
  */
class If {
  val options = Map(
    "A" -> 2,
    "B" -> 3,
    "C" -> 4
  )

  @Test
  def testReturnType = {
    val ifDemo = new If()
    println(ifDemo.getProperty("C")) //Console.println
    println(ifDemo.getProperty("D"))
  }

  def getProperty(key: String): Int = {
    if (options.contains(key))
      options.get(key).get
    else
      1 // the else brunch  result type must be the same with the  if brunch

  }

}
