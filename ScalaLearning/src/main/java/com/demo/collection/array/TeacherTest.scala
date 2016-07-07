package com.demo.collection.array

import org.junit.Assert.assertEquals
import org.junit.Test

/**
  * Created by a549238 on 1/7/14.
  */

class TeacherTest {

  @Test
  def testFilter = {
    val programmers = Array(
      new Teacher("Ted", "Neward", 37, 50000, Array("C++", "Java", "Scala", "C#", "F#", "Ruby")),
      new Teacher("Amanda", "Luncher", 27, 45000, Array("Java", "Scala", "C#", "F#")),
      new Teacher("Luk", "HOban", 32, 45000, Array("C++", "Java", "Scala", "Visual Basic")),
      new Teacher("Scott", "Dav", 37, 50000, Array("Java", "Groovy"))
    )

    //get all scala programmers
    val scalaProgramers = programmers.filter((p) => p.getLessons.contains("Scala"))

    assertEquals(3, scalaProgramers.length)

    scalaProgramers.foreach((p) => p.setPay(p.getPay + 5000))

    assertEquals(programmers(0).getPay, 50000 + 5000)
    assertEquals(programmers(1).getPay, 45000 + 5000)

    assertEquals(programmers(2).getPay, 45000 + 5000)
    assertEquals(programmers(3).getPay, 50000)

    //test another way to create pojo
    val teacher = new Teacher2("Scott", "Dav", 37, 50000, Array("Java", "Groovy"))
    teacher.lastName = "B" //change value success
    teacher.setFirstName("A") //change value success
    println(teacher.getFirstName + " info :\n" + teacher.toString)

  }

}
