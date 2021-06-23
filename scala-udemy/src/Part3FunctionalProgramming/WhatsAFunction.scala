package com.cisco.test
package Part3FunctionalProgramming

object WhatsAFunction extends  App {
  //Use functions as First class elements

  val doubler = new MyFunction[Int,Int] {
    override def apply(element: Int):Int = element * 2
  }
  println(doubler(2))

  //Scala predefined functions
  var stringToIntConverter = new Function[String, Int] {
    override def apply(v1: String): Int = v1.toInt
  }

  println(stringToIntConverter("32")+4)

  val adder:((Int,Int)=> Int) = new Function2[Int,Int,Int] {
    override def apply(v1: Int, v2: Int): Int = v1+v2
  }
  //scala syntactic sugar for function types: [Int,Int,Int] as input,input,result
    //is written as (A,B) => R

  //ALL SCALA FUNCTIONS ARE OBJECTS
}

trait MyFunction[A,B]{
  def apply(x:A): B
}

