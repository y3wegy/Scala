package com.demo.begin

/**
  * Created with IntelliJ IDEA.
  * User: a549238
  * Date: 2/26/13
  * Time: 2:05 PM
  * To change this template use File | Settings | File Templates.
  */
object HelloWorld {
  var s = 0;

  def main(args: Array[String]) {
    println("Hello world!");
    changeS();
    println(s);
    Console.println("++++++++++++++++++++++++++++++++");
    val arrayValue = Array("1", "2", "3"); //create a array

    arrayValue.foreach(println);

    val a: Int = 2;

  }

  def changeS() {
    s = 2;
  }

}
