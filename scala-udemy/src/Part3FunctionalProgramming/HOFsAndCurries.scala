package com.cisco.test
package Part3FunctionalProgramming

object HOFsAndCurries extends  App{
  //takes function as param or returns function as result is called higher order function - HOF
 // val superFunction: (Int, (String,(Int=> Boolean))=>Int ) => (Int => Int) = ???
  //examples: map,filter, flatMap in Mylist

  //function that applies a function n times over value of x
  //nTimes(f, n , x)
  //nTimes(f,3,x) == f(f(f(x))) = nTimes(f, 2,f(x)) == f(f(f(x)))
  //nTimes(f,n,x) = nTimes(f,n-1,f(x))


  def nTimes(f:Int => Int, n:Int, x:Int) : Int =
    if(n <= 0 ) x
    else nTimes(f, n-1,f(x))

  val plusOne = (x:Int) => x+1
  println(nTimes(plusOne,10,1))

//
  //ntb(f,n) = x => f(f(f...(x)
  //increm10 = ntb(plusOne,10)= x=>plusOne(plusOne...(x))
  //val y= increm10(1)
  def nTimesBetter (f:Int => Int, n :Int): (Int => Int)=
    if( n <= 0) (x:Int)=> x
    else (x:Int ) =>  nTimesBetter(f, n-1)(f(x))


  val plus10 = nTimesBetter(plusOne,10)
  println(plus10(1))

  //curried function
  val superAdder:Int => (Int => Int) = (x:Int) => (y : Int) => x+ y

  val add3 = superAdder(3)
  println(add3(10))

  //functions with multiple param lists

  def curriedFormatter(c:String)(x:Double): String= c.format(x)

  val stdFormatter :(Double=> String) = curriedFormatter("%4.2f")
  val preciseFormat: (Double=>String)= curriedFormatter("%10.8f")
  println(stdFormatter(Math.PI))
  println(preciseFormat(Math.PI))

}
