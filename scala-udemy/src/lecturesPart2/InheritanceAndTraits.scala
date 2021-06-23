package com.cisco.test
package lecturesPart2

object InheritanceAndTraits extends  App{

  sealed class Animal{
    val creatureType = "Wild"
      def eat = println("animal nomnom")
  }

  class Cat extends Animal{
    def crunch = {
      eat
      println("crunch")
    }
  }
    val cat = new Cat
    cat.crunch
  //Overriding
  class Dog extends Animal{
    override val creatureType = "pet"
    override def eat = println("Dog crunch")
  }

  //Or
  class Dog2(override val creatureType : String) extends Animal{
    //= "pet"
    override def eat = println("Dog crunch")
  }

  /*similar to writing
  *
  *  class Dog2(dogType : String) extends Animal{
    override val creatureType = dogType
    override def eat = println("Dog crunch")
  }

  * */
  val dog = new Dog
  dog.eat
  println(dog.creatureType)
  val dog2 = new Dog2("pet")
  println(dog2.creatureType)



  //Constructor
  class Person(name:String , age:Int){
    def this(name:String) = this (name,0)
  }
  class Adult(name:String, age:Int, idCard:String) extends Person(name,age)
   class Adult1(name:String, age:Int, idCard:String) extends Person(name)  //is also correct



//type substitutions (polymorphism)
  val unknownType: Animal = new Dog2("K9")
  unknownType.eat



  //super
  class Dog3(override val creatureType : String) extends Animal{
    //= "pet"
    override def eat = {
      super.eat
      println("Dog crunch3")
    }
  }

  val dog3 = new Dog3("d3")
  dog3.eat


  //preventing overriding
  //1. use key word final on member, and class
  //2. sealed the class ==> extends the class in this class , but prevent extension in other files

}

