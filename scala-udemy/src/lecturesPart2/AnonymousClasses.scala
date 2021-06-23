package com.cisco.test
package lecturesPart2

object AnonymousClasses extends  App{
  abstract class Animal{
    def eat :Unit
  }

  val funnyAnimal : Animal = new Animal {
    override def eat: Unit = println("funnyAnimal Anonymous method")
  }
  funnyAnimal.eat
  println(funnyAnimal.getClass)

  /*
  * class AnonymousClasses$$anon$1 extends Animal{
     override def eat: Unit = println("funnyAnimal Anonymous method")
  }
  * val funnyAnimal : Animal = new AnonymousClasses$$anon$1
   */

}
