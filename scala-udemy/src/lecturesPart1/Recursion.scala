package com.cisco.test
package lecturesPart1

import scala.annotation.tailrec
import scala.jdk.Accumulator

object Recursion extends  App{


  def factorial(n : Int): Int=
    if ( n <=1) 1
    else {
     println (" Computing factorial of " + n + " but need to compute factorial of "+ (n-1))
     val  result = n * factorial(n - 1)  //NOT TAIL RECURSION: it need to store the intermediate result
      println (" Computed factorial of " + n )
    result
    }

  println(factorial(5))

 // print(factorial(5000)) //if it crosses 45k it gives stackoverflow error bcz of repeated recursion

  def factorialSolution (n : Int) : BigInt =
    {
      println ("Inside factorialSolution")
      @tailrec
      def factHelper(x: Int, accum: BigInt): BigInt=
        if (x <= 1) accum
        else factHelper(x - 1, x * accum)   //TAIL RECURSION: use the recursive call as last expression
      factHelper(n,1)
    }
  println(factorialSolution(5000))
  /*
  factorialSolution(10) = factHelper (10, 1)
    =factHelper(9, 10 * 1)
    =factHelper(8, 9 * 10 * 1)
    =factHelper(7, 8 *9 * 10 * 1)
    ..
    =factHelper(2, 3 * 4* ... 9 * 10 * 1)
    =factHelper(1, 2 *3 *...9 * 10 * 1)
     = 2 * 3 *...* 10 * 1


   */

  /*
  write a tail recursive function for :
   1. concatenate a string n times
  2.isprime
  3. fibonacci

   */

  def tailRecConcateString(n: Int , givenString: String, accumulator:String): String =
  if (n <=0) accumulator
  else
    tailRecConcateString(n-1,givenString, givenString+accumulator)

  println(tailRecConcateString(3,"hello",""))



  def tailRecIsPrime(n:Int):Boolean ={
 def isPrime(t:Int , isStillPrime:Boolean):Boolean=
   if(!isStillPrime) false
    else if(t <=1 ) true
   else isPrime(t-1, n % t != 0 && isStillPrime)
     isPrime( n/2, true)
 }

  println(tailRecIsPrime(2000))
  println(tailRecIsPrime(23))


  def tailRecFibonacci(n:Int):Int={
    def fiboTail(i:Int, last: Int,nextLast:Int):Int=
      if (i >= n ) last
      else fiboTail(i+1,last+nextLast,last)
      if(n<=2 ) 1
      else fiboTail(2,1,1)
    }

    println(tailRecFibonacci(8))



}
