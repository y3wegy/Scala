package com.demo.casepkg

/**
  * Created by a549238 on 1/9/14.
  */
class CaseTest {
  val person1 = Person("Chen", "Rui", 23)
  val person2 = Person("Chen", "HaiRui", 20)
  val person3 = Person("Yu", "LiLi", 24)

  def testCase(): Unit = {
    println(indentfy(person1))
    println(indentfy(person2))
    println(indentfy(person3))
  }

  def indentfy(p: Person): String = {
    "Process indentfy and Person:" + (
      p match {
        case Person("Chen", _, _) => "This Person is a good man "

        case Person(_, _, age) if (age > 40) => "This Person is old."

        case Person(firstName, lastName, age) if (age > 21) => "Can you tell me why you are so NB?"

        case _ => "I have no idea!"
      }
      )

  }

}

case class Person(first: String, last: String, age: Int)
