package com.cisco.test
package lecturesPart4

object PatternsEverywhere extends App {
  try{
    //code
  } catch {
    case e: RuntimeException =>"Runtime"
    case npe:NullPointerException => "NullpointerException"
    case _ => "something else"
  }

  /*catches are actually matches
  * try{
  * //code
  * }catch(e){
  *  e match {
        case e: RuntimeException =>"Runtime"
        case npe:NullPointerException => "NullpointerException"
        case _ => "something else"
  * }
  * }
  *
  * */

  //big idea #2
  val list =List(1,2,3,4)
  val evenOnes= for{
    x <- list if( x %2 ==0)
  } yield 10 * x
  //generators are also based on PM

  val tuples = List((1,2),(3,4))
  val filterTuples = for{
    (first,second) <- tuples
     } yield (first * second)
  //case classes , :: operators etc

  //big idea #3
  val tuples3 = (1,2,3)
  val (a,b,c) = tuples3

  println(b)


  val head :: tail = list
  println(head)
  println( tail)

  //big idea #4
  //partial function
  val mappedList = list.map{
    case v if v % 2 ==0 => v+ " is even"
    case 1 => "is one"
    case _ => "something else"
  } //partial function literal are based on pattern matching

  val mappedList2 = list.map{ x => x match {

    case v if v % 2 ==0 => v+ " is even"
    case 1 => "is one"
    case _ => "something else"
  } }

  println(mappedList)
  println(mappedList2)
}
