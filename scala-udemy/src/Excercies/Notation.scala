package com.cisco.test
package Excercies

import scala.language.postfixOps

object Notation extends  App{


  class Person(val name:String,favMovie:String ,val age: Int =1){
    def likes(movie:String):Boolean = movie ==favMovie

    def +(person: Person): String = s"${this.name} is hanging out with ${person.name}"
    def +(nickName: String): Person = new Person(s"$name($nickName)" , favMovie)

    def unary_+ : Person = new Person(name,favMovie,age+1)
    def unary_! : String = s"$name Seriously?!"

    def isAlive : Boolean = true

    def apply():String = s"Hi my name is $name and i like $favMovie"
    def apply(n:Int):String = s"$name watched $favMovie $n times"

    def learns(thing: String) : String = s"$name is learning $thing"
    def learnsScala = this learns("Scala")

  }

  val mary = new Person("Mary", "Inception")

  println(mary.likes("Inception"))
  println(mary likes "Inception" )  //operator notation ,or infix notation --> syntactic sugar

  val tom= new Person("tom", "Fight club")
  println(mary +(tom))
  println(mary.+(tom))
  //"operator in Scala
  //ALL operators are methods

  println(1 +2)
  println(1.+(2))


  //Prefix notation
  val x = -1  //equivalent to .unary_-
  val y = 1.unary_-
  // .unary_- works with + - ~ !
 println(!mary)
  println(mary.unary_!)


  //postfix notation
println(mary.isAlive)
println(mary isAlive)


  //apply notations
  println(mary.apply())
  println(mary()) //equivalent


  /*
  Exercises
1. Overload + operator
 mary+ "the rockstar" => new person mary ("the rockstar")

 2. add an age to the person class
 add a unary + operator => new person with the age+1
 +mary => mary with the age incremented

3. "learns" method  in this class --> mary learns Scala
    add "learnsScala" and calls learns with "Scala"
    use it in postfix annotation

 4. overload apply method
 mary.apply(2) --> mary watched Inception 2 times


   */

//1

  println((mary + "the rockstar")())

  //2
  println(+mary.age)

  //3
  println(mary learnsScala)

  //4
  println(mary(4))
}


