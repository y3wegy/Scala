package com.demo.collection.array

/**
  * Created by a549238 on 1/7/14.
  *
  * use annotions to create getter and setter method
  */
class Teacher2(var fn: String, var ln: String, var a: Int, var p: Int, var lns: Array[String]) {

  @scala.beans.BeanProperty
  var firstName = fn

  @scala.beans.BeanProperty
  var lastName = ln

  @scala.beans.BeanProperty
  var age = a

  @scala.beans.BeanProperty
  var pay = p

  @scala.beans.BeanProperty
  var lessons = lns

  override def toString =
    "Teacher2-> firstName:" + getFirstName + ",lastName:" + getLastName + ",age:" + getAge + ",lseesons:" + getLessons.foreach(println)

}
