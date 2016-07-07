package com.demo.collection.list

import org.junit.Test

/**
  * Created by a549238 on 1/8/14.
  */
class ListTest {

  val other: List[String] = List("AA", "BB")
  val last = other.last

  @Test
  def testList() = {
    val myVipList = "A" :: "B" :: "C" :: "D" :: other
    println("myVipList length:" + myVipList.length)

    val myVipList2 = myVipList :: myVipList :: myVipList // the last :: right element must be a list
    println("myVipList2 length:" + myVipList2.length) //length:8    non -last list  will as be each one
    myVipList2.foreach(println)
  }

  def count(list: List[String]): Int = {
    list match {
      case h :: t => count(t) + 1 // _::t use this can make  list head  can not be used
      case last => 1
    }
  }

  @Test
  def testLength() = {
    val list = "A" :: "B" :: "C" :: "D" :: other
    println("list length:" + count(list))

  }


}
