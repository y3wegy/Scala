package com.demo.algorithm

import org.junit.Test

/**
  * Created by a549238 on 3/28/2016.
  */
class ChangeMoney {
  def countChange(money: Int, coins: List[Int]): Int = {
    if (money == 0) {
      1
    } else if (coins.size == 0 || money < 0) {
      0
    }
    else {
      countChange(money, coins.tail) + countChange(money - coins.head, coins)
    }
  }

  @Test
  def testMoney(): Unit = {
    val money: Int = 1000;
    val coins: List[Int] = List(1, 5, 10)
    print(countChange(money, coins))
  }

}
