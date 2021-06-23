package com.cisco.test
package lecturesPart1

object StringOps extends App {

  val str: String = "Hello, I am learning scala"

  println(str.charAt(2))
  println(str.substring(7, 11))

  println(str.split(" ").toList)
  println(str.startsWith("H"))


  //Scala specific functions

  val aNumberString = "45"
  val aNumber = aNumberString.toInt
  println('a' +: aNumberString :+ 'z')

  println(str.reverse)
  println(str.take(2)) //He


  //Scala specific interpolators


  //S-interpolaters
  val name = "David"
  val age = 13

  val greeting = s"Hello, my name is $name. I am $age years old"
  val anotherGreeting = s"Hello, I am $name. I am turning ${age + 1} years old"
  println(greeting)
  println(anotherGreeting)

  //F-interpolaters

  val speed = 1.2f
  val myth = f"$name%s can eat $speed%2.2f burgers per minute"
  println(myth)


  //raw-interpolator

  println(raw"this is a \n newline")

  val escaped = "this is a \n newline"

  println(escaped)
}
