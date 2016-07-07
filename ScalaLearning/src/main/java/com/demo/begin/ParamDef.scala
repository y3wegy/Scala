package com.demo.begin

/**
  * Created with IntelliJ IDEA.
  * User: a549238
  * Date: 2/26/13
  * Time: 3:18 PM
  * To change this template use File | Settings | File Templates.
  */
/*
这个例子是测试方法预设值
 */
object ParamDefault {
  def main(args: Array[String]) {
    println(joiner(List("123", "asdasd")))
    println(joiner(List("234", "dfggg"), sep = ";")) //此处指定了形参里面的"sep"为“；”
    println(joiner(sep = ";", list = List("fdg", "cvb"))) //此处可以发现命名参数可以随意的调换参数的顺序；但是调换顺序后好像全部的参数都得使用命名参数，不然会报错的

  }

  /*
  连接字符串,默认使用“，”连接
   */
  def joiner(list: List[String], sep: String = ","): String =
    list.mkString(sep)

}
