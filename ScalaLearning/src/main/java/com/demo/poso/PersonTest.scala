package com.demo.poso

import org.junit.Test

/**
  * Created by a549238 on 1/8/14.
  */
class PersonTest {
  @Test
  def testExtends() = {
    val rui: Person = new Chinese("Chen,Rui", 23, "male")
    //rui.age =24  //not work ,value type is val
    rui.custom
    rui.doWork

    rui.work =
      (person: Person) => println("After change  work context")

    rui.doWork
  }

}
