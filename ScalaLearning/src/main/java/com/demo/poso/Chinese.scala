
package com.demo.poso

/**
  * Created by a549238 on 1/8/14.
  */
final class Chinese(name: String, age: Int, sex: String) extends Person(name, age) {

  def weight: Int = age

  override def custom() =
    println("We like noodle");

  override def toString(): String =
    "Person[name:" + name + ",age:" + age + ",sex:" + sex + "]"

}