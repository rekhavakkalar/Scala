package com.cisco.test
package lecturesPart2

object Generics extends  App {

  class MyList [+A]{
    //use the type "A"
  def add[B >: A](element: B) : MyList[B] = ???
    /*
    *   A= cat
    *   B = Animal
    * */
  }

  class MyMap[Key,Value]


//  val listOfIntegers = new myList[Int]
 // val listOfStrings = new myList[String]

  //generic methods
  object MyList{
    def Empty[A]:MyList[A] = ???

  }


  val emptyListOfInt = MyList.Empty[Int]

  //Variance problem
  class Animal
  class Cat extends Animal
  class Dog extends Animal

  //1. yes List[Cat] extends List[Animal] = COVARIANCE

  class covariantList[+A]

  val animal: Animal = new Cat
  val animalList: covariantList[Animal] = new covariantList[Cat]
  //animalList.add(new Dog) ??

  //2 .  yes List[Cat] extends List[Animal] = INVariant

  class InvariantList[A]
  val invariantList : InvariantList[Animal] = new InvariantList[Animal]

  //3. Contra variant list
  class ContraVariantList[-A]
  val contraVariantList: ContraVariantList[Cat] = new ContraVariantList[Animal]
  //doesnt make much sense in this case, lets take next example

  class Trainer[-A]
  val trainerList: Trainer[Cat] = new Trainer[Animal]


  //bounded types

  class Cage[A <: Animal](animal: A){
    val cage = new Cage(new Dog)

  }


  //expand Mylist to be generic
}
