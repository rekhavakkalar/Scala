package com.cisco.test
package lecturesPart2

object Exceptions extends App{

  val x:String= null
//  println(x.length)

  //throwing  exception
  //val weirdValue:String = throw new NullPointerException

  //and catching exception
  def getInt(withException:Boolean):Int=
    if(withException) throw new RuntimeException("No Int for you")
    else 42

 val potentialval =  try{
    getInt(true)
  } catch {
    case e: RuntimeException => println("Caught exception") // return type will be Anyval
     // case e: RuntimeException => 42  return type will be Int

  } finally {
   //doesnt influence the return type of this expression
   //use only for side effects
    println("Inside finally")
  }


//3. define your own exception
  class MyException extends Exception
  val exception = new MyException

  throw exception


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
}
