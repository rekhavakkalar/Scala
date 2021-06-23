package com.cisco.test
package Part3FunctionalProgramming

object AnonymousFunction extends  App{
  val doubler = new Function[Int,Int] {
    override def apply(x: Int): Int = x * 2
  }
// in scala this is written as below (Lambda)/Anonymous function
  val doubler1 :(Int => Int) = (x:Int) => x * 2

  //
  val doubler2 = (x:Int) => x * 2


  //multiple params
  val adder:(Int,Int) => Int = (a:Int,b:Int) => a+b

  //No params
  val dosomething :() => Int = () => 3
  println(dosomething)  //function itself
  println(dosomething()) //actual call to function's method


  //curly braces with lambdas
  val stringToInt ={ (str:String) =>
    str.toInt
  }

  //MOAR Syntactic Sugar
  val niceInc: Int => Int = (x:Int) => x + 2
  //equivalant to writing :
  val niceInc2: Int => Int = _ + 2

  val adder1 :(Int,Int) => Int = (a:Int, b:Int) => a + b
  //equivalant to
  val adder2 : (Int, Int) => Int = _ + _

  /*Exercises
  * 1. MyList --> replace all FunctionX calls with lambda
  * 2. Define / re-write special adder (Curried) as an anonymousfunction
  * */

  //2
  //Function[Int, Function[Int,Int]]
  /* val superAdder : Function1[Int, Function1[Int,Int]] = new Function1[Int, Function1[Int,Int]] {
    override def apply(x:Int): Function1[Int,Int] = new Function1[Int,Int] {
      override def apply(y: Int): Int = x + y
    }
  } */

  val superAdder1 = (x:Int) => (y: Int) => x + y

  println(superAdder1(3)(5))  // Curried Function --> multiple params --> returns a function
}
