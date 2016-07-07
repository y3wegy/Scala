package com.demo.collection.tuple

/**
  * Created with IntelliJ IDEA.
  * User: a549238
  * Date: 2/26/13
  * Time: 4:15 PM
  * To change this template use File | Settings | File Templates.
  */

/*
测试元祖写法,元组是一个不限制元素类型的数组
 */
object TuplesDemo extends App {
  val t = tupleator("Hello", 1, 2.33)
  val (t1, t2, t3) = tupleator("world", "!", 0x22)
  println("The tuple:" + t + ";   itmes:" + t._1 + "  " + t._2 + "  " + t._3)

  def tupleator(x1: Any, x2: Any, x3: Any) = (x1, x2, x3)

  println(t1 + " " + t2 + " " + t3)

}
