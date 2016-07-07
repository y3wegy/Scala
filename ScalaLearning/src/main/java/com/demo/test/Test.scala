package com.demo.test

import org.junit.Test

/**
  * Created by a549238 on 1/17/14.
  */
class TestCase {

  @Test
  def testUnit(): Unit = {
    val s = "123"
    if (s eq "123")
      println("123")
  }

}
