package com.cisco.test
package Part3FunctionalProgramming

object MapFlatmapFilter extends  App{
  val list = List(1,2,3)
  println(list.head)
  println(list.tail)

  println(list.map(_ + 1))
  println(list.map(_ + " is a number"))

  println(list.filter(_ % 2 ==0))

  //flatmap
val topair = (x: Int) => List( x, x+1)
  println(list.flatMap(topair))


  //print all combination of two lists
  val numbers = List(1,2,3,4)
  val chars= List('a','b','c','d')
  val colors = List("black", "white")
  //print "a1", "a2"......"d4"


 // iterations

  val combinations = numbers.flatMap( n => chars.map( c => ""+ c + n))
  println(combinations)

  val combo3 = numbers.flatMap( n => chars.flatMap(c => colors.map(color => ""+c+n+"-"+color)))
  println(combo3)


  //foreach
  list.foreach(println)

  //shorthands --> for-comprehensions
  val forCombinations = for {
    n <- numbers if n %2 ==0   //numbers.filter( _ % 2 ==0).flatMap(...
    c <- chars
    color <- colors
  } yield ""+c+n+"-"+color

  println(forCombinations)

  // list.foreach(println) can be written as
   for{
    n <- numbers
  }println(n)

  //syntax overload
  print(list.map { x => x *2 })

  /*Excercies
  * 1. MyList supports for-comprehensions?
  *
  * map( f:A => B)=> MyList[B]
  * filter(p: A => Boolean)=>MyList[A]
  * flatmap(f: A=> MyList[B])=> MyList[B]
  * 2. Implement collection of at most ONE element - MayBe(+T)
  *   -map,flatmap,filter
  * */

  //1. MyListHOF : for-comprehensions
 //2

}
