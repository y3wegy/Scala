package com.demo.poso

/**
  * Created by a549238 on 1/8/14.
  */
final class Africa(name: String, age: Int, sex: String) extends Person(name, age) {
  val weight: Int = age * 4 // change the method to field type  ,and this is ok

  def custom: Unit =
    println(" I like SunShine !")
}
