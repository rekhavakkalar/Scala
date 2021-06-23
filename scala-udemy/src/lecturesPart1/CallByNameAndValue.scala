package com.cisco.test
package lecturesPart1

object CallByNameAndValue extends  App{

  def calledByValue(x:Long):Unit={
    println("By Value" +x)
    println("By Value" +x)
  }

  def calledByName(x: => Long):Unit={
    println("By Name" +x)   // similar to wrting as   println("By Name" +System.nanoTime())
    println("By Name" +x)   // similar to wrting as   println("By Name" +System.nanoTime())
  }

  calledByValue(System.nanoTime());
  calledByName(System.nanoTime())

  /*
  By Value260171832016500
  By Value260171832016500
  By Name260171914350800
  By Name260171914396900
   */


  def infinite():Int = 1+ infinite()
  def printFirst(x:Int, y : => Int) = println(x)   //Since y is not used infinite method is not called
  printFirst(34, infinite())
}
