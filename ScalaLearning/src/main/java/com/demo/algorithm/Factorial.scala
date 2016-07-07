package com.demo.algorithm

import scala.annotation.tailrec

/**
  * Created by a549238 on 3/25/2016.
  */
class Factorial {
  def factorial(n: Int): Int = {
    @tailrec
    def loop(acc: Int, n: Int): Int =
      if (n == 0) acc else loop(n * acc, n - 1)
    loop(1, n)
  }


}
