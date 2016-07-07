package com.demo.sort

import org.junit.Test

/**
  * Created by a549238 on 3/24/2016.
  */
class QuickSort {
  def quickSort(xs: List[Int]): List[Int] = {
    if (xs.isEmpty) xs
    else
      quickSort(xs.filter(x => x < xs.head)) ::: xs.head :: quickSort(xs.filter(x => x > xs.head))
  }

  @Test
  def testSort(): Unit = {
    val list1 = List(1, 2)
    val list2 = List(3, 4)

    println("list1::list2: " + (list1 :: list2)) //List(List(1, 2), 3, 4)
    println("list1:::list2: " + (list1 ::: list2)) //List(1, 2, 3, 4)
  }
}
