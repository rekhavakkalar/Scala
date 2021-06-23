package com.cisco.test
package Excercies

object HOFsAndCurriesExc extends  App{
  val superAdder : Function1[Int, Function1[Int,Int]] = new Function1[Int, Function1[Int,Int]] {
    override def apply(x:Int): Function1[Int,Int] = new Function1[Int,Int] {
      override def apply(y: Int): Int = x + y
    }
  }
  /*
  * 1. Expand MyList
  *  -foreach method A => Unit
  *       Ex: [1,2,3].foreach((x)=>println(x))
  *  - sort function ((A,A) => Int) => MyList
  *      Ex: [1,2,3].sort((x,y) => y-x) => [3,2,1]
  *
  *  -zipWith(MyList, (A,A) => B) => MyList(B)
  *     Ex: [1,2,3].zipWith([4,5,6], x*y) => [1*4, 2*5,3*6] => [4,10,18]
  * -fold
  *   (start)(function) => value
  *  [1,2,3].fold(0)(x +y) =>6
  *
  * 2. toCurry(f:(Int,Int) => Int)=> (Int => Int => Int)
  *    fromCurry( Int => Int => Int) => (Int , Int) => Int
  * 3.  compose(f,g) =>x => f(g(x))
  *     andThen(f,g) => x => g(f(x))
  * */

  def toCurry(f:(Int,Int)=> Int) : (Int=> Int => Int) = x => y => f(x,y)
  def fromCurry(f: (Int => Int => Int)) : (Int , Int) => Int=
    (x,y) => f(x)(y)

  //def compose(f: Int =>Int,g: Int =>Int):  Int =>Int= x => f(g(x)
  def compose[A,B,T](f: A =>B ,g: T =>A):  T =>B=
    x => f(g(x))

  //def andThen(f: Int =>Int,g: Int =>Int):  Int =>Int= x => g(f(x))
  def andThen[A,B,C](f: A => B,g: B =>C):  A =>C =
    x => g(f(x))



  def superAdder2:(Int=> Int => Int)  = toCurry(_ + _)
  def add4 = superAdder2(4)
  println(add4(17))


  val simpleAdder = fromCurry(superAdder)
  println(simpleAdder(2,17))

  val add2 = (x:Int ) => x + 2
  val times3= (x: Int) => x * 3

  val composed = compose(add2,times3)   // 4 * 3 = 12 then 12 + 2 ===14
  val ordered = andThen(add2,times3)  //(4 +2 )* 3  == 18
  println(composed(4))
  println(ordered(4))


}
