package com.cisco.test
package Excercies

object FunctionExercise extends App {
/* 1. a function that takes 2 strings and concatenate
*  2. MyList transform -> MyPredicate and Mytransformer to Function (MyListFunc.scala)
*  3. define a Function which takes an Int and returns another function which takes Int and returns an int
*     -whats the type of this function
*     - how to do it?
*
* */
  //1
  val concat:((String,String)=>String) = new Function2[String, String, String] {
    override def apply(str1: String,str2 : String):String = str1+" "+str2
  }
  println(concat("Hello", "Scala"))

  //3
  //Function[Int, Function[Int,Int]]
  val superAdder : Function1[Int, Function1[Int,Int]] = new Function1[Int, Function1[Int,Int]] {
    override def apply(x:Int): Function1[Int,Int] = new Function1[Int,Int] {
      override def apply(y: Int): Int = x + y
    }
  }

  val adder = superAdder(3)
  println(adder(4))
  println(superAdder(3)(5))  // Curried Function --> multiple params --> returns a function
}
