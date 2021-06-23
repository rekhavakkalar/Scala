package com.cisco.test
package lecturesPart2

object AbstractType extends  App{

  abstract  class Animal{
    val creatureType :String
    def eat:Unit
  }

  class Dog extends Animal{
    override val creatureType: String = "K9"
     def eat: Unit = println("crunch crunch")  //override is not mandatory for abstract override methods/vals
  }


  trait  Carnivor{
    def eat (animal: Animal):Unit
    def preferredMeal: String = "freshMeat"
  }

  trait ColdBlooded
  class Crocodile extends Animal with Carnivor with ColdBlooded{
    override val creatureType: String = "croc"

    override def eat: Unit = println("croc nom nom")

    override def eat(animal: Animal): Unit = println(s"I am a croc and I am eating ${animal.creatureType}")
  }


  val dog = new Dog
  val croc = new Crocodile
  croc.eat(dog)

  //diff between traits and abstract class
  //1- traits do not have constructor params
  //2- only extends 1 class, multiple traits may be inherited by same class
  // 3- traits are behavior but abstract class = type of "thing"
}
