package com.demo.packagedemo.calc

/**
  * Created by a549238 on 1/9/14.
  */
import scala.util.parsing.combinator.JavaTokenParsers


  private[calc] abstract class Expr

  private[calc] case class Variable(name: String) extends Expr

  private[calc] case class Number(value: Double) extends Expr

  private[calc] case class UnaryOp(operator: String, arg: Expr) extends Expr

  private[calc] case class BinaryOp(operator: String, left: Expr, right: Expr) extends Expr

  object Calc {
    def evalute(e: Expr): Double = {
      simply(e) match {
        case Number(x) => x
        case UnaryOp("-", x) => -(evalute(x))
        case BinaryOp("+", x1, x2) => evalute(x1) + evalute(x2)
        case BinaryOp("-", x1, x2) => evalute(x1) - evalute(x2)
        case BinaryOp("*", x1, x2) => evalute(x1) * evalute(x2)
        case BinaryOp("/", x1, x2) => evalute(x1) / evalute(x2)

      }
    }

    def simply(e: Expr): Expr = {
      e match {
        case UnaryOp("-", UnaryOp("-", x)) => simply(x)
        case UnaryOp("+", x) => simply(x)
        case BinaryOp("*", x, Number(1)) => simply(x)
        case BinaryOp("*", Number(1), x) => simply(x)
        case BinaryOp("*", x, Number(0)) => Number(0)
        case BinaryOp("*", Number(0), x) => Number(0)
        case BinaryOp("/", Number(0), x) => Number(0)
        case BinaryOp("/", x, Number(1)) => simply(x)
        case BinaryOp("/", x1, x2) if (x1 == x2) => Number(1)
        case BinaryOp("+", x, Number(0)) => simply(x)
        case BinaryOp("+", Number(0), x) => simply(x)
        case _ => e;
      }

    }

  }

  object CalcV2 {
    def evalute(e: Expr): Double = {
      simplfy(e) match {
        case Number(x) => x
        case UnaryOp("-", x) => -(evalute(x))
        case BinaryOp("+", x1, x2) => evalute(x1) + evalute(x2)
        case BinaryOp("-", x1, x2) => evalute(x1) - evalute(x2)
        case BinaryOp("*", x1, x2) => evalute(x1) * evalute(x2)
        case BinaryOp("/", x1, x2) => evalute(x1) / evalute(x2)

      }
    }

    def simplyTop(e: Expr): Expr = {
      e match {
        case UnaryOp("-", UnaryOp("-", x)) => x
        case UnaryOp("+", x) => x
        case BinaryOp("*", x, Number(1)) => x
        case BinaryOp("*", Number(1), x) => x
        case BinaryOp("*", x, Number(0)) => Number(0)
        case BinaryOp("*", Number(0), x) => Number(0)
        case BinaryOp("/", Number(0), x) => Number(0)
        case BinaryOp("/", x, Number(1)) => x
        case BinaryOp("/", x1, x2) if (x1 == x2 && x2 != 0) => Number(1)
        case BinaryOp("+", x, Number(0)) => x
        case BinaryOp("+", Number(0), x) => x
      }

    }

    def simplfy(e: Expr): Expr = {
      val simpleSub = e match {
        case BinaryOp(op, left, right) => BinaryOp(op, simplfy(left), simplfy(right))
        case UnaryOp(op, oprend) => UnaryOp(op, simplfy(oprend))
        case _ => e
      }

      simplyTop(simpleSub)
    }

    def oldParse(text: String) = {
      val results = oldParser.parse(text)
      System.out.println("parsed " + text + " as " + results + " which is a type " + results.getClass)
    }
  }

  object oldParser extends JavaTokenParsers {
    def expr: Parser[Any] = term ~ rep("+" ~ term | "-" ~ term)

    def term: Parser[Any] = factor ~ rep("*" ~ factor | "/" ~ factor)

    def factor: Parser[Any] = floatingPointNumber | "(" ~ expr ~ ")"

    def parse(text: String) = {
      parseAll(expr, text)
    }

  }

  object AnyParse extends JavaTokenParsers {
    def expr: Parser[Any] = (term ~ "+" ~ term) | (term ~ "-" ~ term) | term

    def term: Parser[Any] = (factor ~ "*" ~ factor) | (factor ~ "/" ~ factor) | factor

    def factor: Parser[Any] = floatingPointNumber | "(" ~> expr <~ ")"

    def parse(text: String) = {
      parseAll(expr, text)
    }
  }
