package com.demo.traversaldemo

/**
  * Created with IntelliJ IDEA.
  * User: a549238
  * Date: 2/27/13
  * Time: 10:18 AM
  * To change this template use File | Settings | File Templates.
  */
/*
测试for循环里面不同写法的性能问题
 */
object TraversalDemo {
  def main(args: Array[String]): Unit = {
    val set = 1 until 1000
    println("function1 use time:" + time(function1(set), 10000))
    println("function2 use time:" + time(function2(set), 10000))
    /*
    output:
    function1 use time:188
    function2 use time:63
     */

  }

  def time(call: => Unit, count: Int): Long = {
    var cou = count
    val start = System.currentTimeMillis()
    while (cou > 0) {
      call
      cou -= 1
    }
    System.currentTimeMillis() - start
  }

  /*
  偶数求和
   */
  def function1(set: Range) {
    var sum = 0
    for (item <- set; if (item % 2 == 0))
      sum += item
  }

  def function2(set: Range) {
    var sum = 0
    for (item <- set)
      if (item % 2 == 0)
        sum += item
  }
}
