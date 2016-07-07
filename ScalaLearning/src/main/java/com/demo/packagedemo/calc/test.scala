package com.demo.packagedemo.calc

import org.junit.Assert.assertEquals

/**
  * Created by a549238 on 1/9/14.
  */
import org.junit.Test

  class ClacTest {

    @Test
    def AstTest() = {
      val n1 = Number(5)
      assertEquals(5, n1.value, 1.0)
    }

    @Test
    def equlitTest() = {
      val binop: BinaryOp = BinaryOp("+", Number(2), Number(3))
      assertEquals(Number(2), binop.left)
      assertEquals(Number(3), binop.right)
      assertEquals("+", binop.operator)

      assertEquals(5, Calc.evalute(binop), 1.0)
    }

    @Test
    def testParse = {
      val expressions = List(
        "5",
        "(5)",
        "5 + 5",
        "(5 + 5)",
        "5 + 5 + 5",
        "(5 + 5) + 5",
        "(5 + 5) + (5 + 5)",
        "(5 * 5) / (5 * 5)",
        "5 - 5",
        "5 - 5 - 5",
        "(5 - 5) - 5",
        "5 * 5 * 5",
        "5 / 5 / 5",
        "(5 / 5) / 5"
      )

      for (x <- expressions)
        Console.println(x + " = " + AnyParse.parse(x))
    }

  }
