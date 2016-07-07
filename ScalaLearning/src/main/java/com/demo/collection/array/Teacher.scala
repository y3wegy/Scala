package com.demo.collection.array

/**
  * Created by a549238 on 1/7/14.
  */
class Teacher(var firstName: String, var lastName: String, var age: Int, var pay: Int, var lessons: Array[String]) {
  def getPay = pay

  def setPay(value: Int): Unit = pay = value

  def setLessions(value: Array[String]): Unit = lessons = value

  override def toString =
    "Person-> firstName:" + getFirstName + ",lastName:" + getLastName + ",age:" + getAge + ",lseesons:" + getLessons.foreach(println)

  def getFirstName = firstName

  def setFirstName(value: String): Unit = firstName = value

  def getLastName = lastName

  def setLastName(value: String): Unit = lastName = value

  def getAge = age

  def setAge(value: Int): Unit = age = value

  def getLessons = lessons

}
