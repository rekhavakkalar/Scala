package com.cisco.test
package Part3FunctionalProgramming.Collections

import scala.util.Random

object Sequences extends  App {
  //seq
  val aSequence = Seq(2,3,1,4)
  println(aSequence)  //List(1, 2, 3, 4)

  println(aSequence.reverse)
  println(aSequence(2))
  println(aSequence ++ Seq(5,6,7))
 println(aSequence.sorted)

  //ranges

  val aRange : Seq[Int] = 1 to 10  //or 1 until 10 --> 10 exclusive
  aRange.foreach(println)


  (1 to 10).foreach(x => println("Hello"))

  //list


  val aList= List(1,2,3)
  println(aList.mkString("-|-"))


  //val prepend =42 :: aList
  val prepend =42 +: aList :+ 89
  println(prepend)


  val apples5 = List.fill(5)("apple")
  println(apples5)

//arrays
  val number = Array(1,2,4,5)
  val threeElements = Array.ofDim[String](3)
 println(threeElements)
  threeElements.foreach(println)

  //mutation
  number(2)=0  //syntax sugar for number.update(2,0) at index two
  println(number.mkString(" "))


  //array and seq connection
  val numSeq: Seq[Int] = number  //Implicit conversion
  println(numSeq)


  //vector
  val vector : Vector[Int]= Vector(1,2,3)
  println(vector)


  //vectors vs list
  val maxRuns =1000
  val maxCap = 1000000
  def getWriteTime(collection:Seq[Int]): Double=
    {
      val r= new Random
      val times = for{
        it <- 1 to maxRuns
        } yield {
        val currTime = System.nanoTime()
        //operation
        collection.updated(r.nextInt(maxCap),r.nextInt())
        System.nanoTime()-currTime
      }
      times.sum * 1.0 / maxRuns
    }

  val numberList = (1 to maxCap).toList
  val numberVector = ( 1 to maxCap).toVector

  //keeps reference to tail
  //updating the element in the middle takes time
  println(getWriteTime(numberList))

  // depth of the tree is small
  //needs to replace entire 32 element chunk
  println(getWriteTime(numberVector))


}
