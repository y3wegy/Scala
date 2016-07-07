package com.demo.collection.array

import org.junit.Test

/**
  * Created by a549238 on 1/7/14.
  */
class QuickSort() {

  def quickSort(xs: Array[Int]): Array[Int] = {
    if (xs.length <= 1) xs
    else {
      val povit = xs(xs.length / 2)
      Array.concat(
        quickSort(xs filter (povit >)),
        xs filter (povit ==),
        quickSort(xs filter (povit <)))
    }
  }


  @Test
  def testQuickSort = {
    val arrayValues = Array(3, 5, 2, 6, 3, 0, 23, 34, 10, 9, 7, 6, 45, 68, 14)
    val qucikSort = new QuickSort();
    qucikSort.quickSort(arrayValues).foreach(println)
  }
}
