package com.cisco.test
package lecturesPart2

object CaseClasses extends  App{
/*equals , hashcode tostring*/

  case class Person(name:String, age:Int)
//1. class params are fields
    val jim = new Person("jim",30)
    println(jim.name)
//2. sensible toString
  //println(instance) --> println(instance.toString) -->another form of syntactic sugar
  println(jim)
//3 equals and hashcode implemented OutOfBox
  val jim2 = new Person("jim", 30)
  println(jim ==jim2)
  //4. caseclasses have handy copy methods
  val jim3 = jim.copy(age=34)
  println(jim3)


  //5. case classes have companion objects
  val thePerson = Person
  val mary= Person("Mary", 23)


  //6. CC are serializable
  //helpful in Akka
  //7. CC have extractor patterns = CC can be used in pattern matching

  case object UK{
    def name:String= "the UK of Great Britain and NI"
  }
}

/*expand myList use case classes and case object*/
