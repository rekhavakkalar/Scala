package com.cisco.test
package lecturesPart4

import scala.util.Random

object PatternMatching extends App {

  val random = new Random
  val x= random.nextInt(10)
  val description = x match {
    case 1 => "the ONE"
    case 2 => "double or nothing"
    case 3 => "third time is the chars"

    //default with _ wildcard
    case _ => "Something else"
  }
  println(x)
  println(description)

    //1. Decompose values
  case class Person(name:String,age:Int)
  val bob = Person("Bob",23)
  val greeting = bob match {
    case Person(n,a) if (a < 25) => s"Hi My name is $n  and I am going to US"
    case Person(n,a) => "Hi My name is "+n+ " and I am "+a+" years old"
    case _ => "I dont know who I am"
  }
  println(greeting)

  /*
  * 1. cases are matched in order
  * 2. what if no cases are matched? //scala.MatchError
  * 3. Type of patternmatch expression --> Any (Unified type of all the type of all cases)
  * 4. Pattern matching works really well with case classes
  * */

  //pattern matching on sealed heirarchies
  sealed  class AnimalPlanet
  case class Doguu(breed:String) extends AnimalPlanet
  case class parrotu1(greeting:String) extends AnimalPlanet

  val animal2 :AnimalPlanet = Doguu("Mudhol")

  animal2 match {
    case Doguu(somebreed) => println(s"Matched dog of breed $somebreed ")
  }


  //match everything
  val isEven = x match{
    case n if n%2 ==0 => true
    case _ => false
  }  ///WHY???

  //use
  val normaEvenCheck =x%2 ==0

  /*
  * Excercise
  * simple function uses PM, takes an Expr nd returns an human readable form
  *
  * Sum(Number(2),Number(3)) => 2 +3
  * Sum(Number(2),Number(3),Number(4))=> 2+3+4
  * Prod(Sum(Number(2),Number(3)),Number(3)) => (2+3) * 3
  * Sum(Prod(Number(1),Number(2),Number(4))=> 1*2 +4
  * */
trait Expr
  case class Number (n:Int) extends Expr
  case class Sum(e1:Expr,e2:Expr) extends Expr
  case class Prod(e1:Expr,e2:Expr) extends Expr

  def show(e:Expr):String= e match {
    case Number(n) =>s"$n"
    case Sum(e1,e2) => show(e1) + " + "+ show(e2)
    case Prod(e1,e2) => {
      def mayBeShowParenthesis(exp:Expr)= exp match {
        case Prod(_,_) => show(exp)
        case Number(_) => show(exp)
        case _ => "("+show(exp)+")"
      }
      mayBeShowParenthesis(e1)+" * "+mayBeShowParenthesis(e2)
    }
  }


  println(show(Sum(Number(2),Number(3))))
  println(show(Sum(Sum(Number(2),Number(3)),Number(4))))
  println(show(Prod(Sum(Number(2),Number(3)),Number(3)) ))
  println(show(Prod(Sum(Number(2),Number(3)),Sum(Number(3),Number(4))) ))
  println(show(Sum(Prod(Number(1),Number(2)),Number(4))))
}
