package com.demo.poso

/**
  * Created by a549238 on 1/8/14.
  */
abstract class Person(val name: String, val age: Int) {
  var work: (Person) => Unit =
    (person: Person) => println("I am " + person + " , i do not nothing yet !")

  def custom

  def weight: Int

  override def toString(): String =
    "Person[name:" + name + ",age:" + age + "]"

  def doWork = work(this)
}
