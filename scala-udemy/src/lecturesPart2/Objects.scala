package com.cisco.test
package lecturesPart2

object Objects {
  //Scala doesn't have class level functionality ("static")

  object Person {
    //"static" /" class" - level functionality
    val N_EYES = 2

    def canFly: Boolean = false

    def apply(mother: Person, father: Person): Person = new Person("Bobbie")
  }

  class Person(val name: String) {
    //instance-level functionality
  }

  //both class and Object of Person - this is called COMPANIONS
  def main(args: Array[String]): Unit = {
    println(Person.N_EYES)
    println(Person.canFly)
    //Scala Object = Singleton instance
    val mary = Person
    val john = Person
    println(mary == john) //true

    val mary1 = new Person("mary")
    val john1 = new Person("john")

    println("Are classes same? " + (mary1 == john1)) //false

    val person1 = Person
    val person2 = Person
    println("objects are same? " + (person1 == person2))

    val bobbie = Person(mary1, john1) //or Person.apply(mary1,john1)

    //Scala applications = Scala object with --> def main(args:Array(String)):Unit

  }
}
