package com.cisco.test
package Excercies

object ExceptionExamples extends  App{


  /*
  * 1 crash program with with out of memory error (allocate more mem that jvm)
  * 2 crash the SOError
  * 3 pocket calculator --> add (x, y)
  *    multiply(x,y) ,
  *    divide(x,y),
  *    subtract(x,y)
  *    --> throw custom exception  for add throw OverflowException if exceeds Int.MAX_VALUE
  *                                for subtract throw underflowException if exceeds Int.MIN_VALUE
  *                                for division MathCalculationException for division by 0
  * */



  // 1 //  val array = Array.ofDim(Int.MaxValue)

  //2
  //def infinite:Int = 1 +infinite
  // val noLimit =infinite

  //3
class OverflowException extends RuntimeException
class UnderflowException extends RuntimeException
  class MathCalculationException extends RuntimeException("Division By Zero")


  object PocketCalculator{

    def add(x:Int, y:Int): Int = {
      if( (x > 0 &&  y > 0 && (x+y) < 0 )) throw new OverflowException
      else if (x <0 && y < 0 && (x + y ) > 0 ) throw new UnderflowException
      else (x + y)
    }

    def subtract(x:Int, y:Int): Int = {
      val result = x - y
      if( x > 0 && y < 0 && result < 0 ) throw  new OverflowException
      else if( x < 0 && y > 0 && result > 0 ) throw  new UnderflowException
      else result
    }

    def multiply(x:Int, y:Int): Int = {
      val result = x * y
      if( x > 0 && y > 0 && result < 0 ) throw  new OverflowException
      else if( x < 0 && y < 0 && result < 0 ) throw  new OverflowException
      else  if( x > 0 && y > 0 && result < 0 ) throw  new OverflowException
      else if( x < 0 && y < 0 && result > 0 ) throw  new UnderflowException
      else  if( x < 0 && y > 0 && result > 0 ) throw  new UnderflowException
      else result
    }

    def divide(x:Int, y:Int) = {
      println("is y zero"+ (y == 0))

      if (y == 0 ) throw new MathCalculationException
      else x / y
    }
  }

  //println(PocketCalculator.add(Int.MaxValue, 10))
 // println(PocketCalculator.divide(12, 0))


}
