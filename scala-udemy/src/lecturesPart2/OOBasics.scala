package com.cisco.test
package lecturesPart2

object OOBasics extends  App{

  val person = new Person("John",26)
  println(person.age)
  println(person.x)
  person.greet("David")
  person.greet()

  val author = new Writer("Steven", "Arthur",1920)
  val imposter = new Writer("Steven", "Arthur",1920)
  val novel = new Novel("Great expectations", 2000,author)
  println(author.fullName())
  println(novel.authorAge)
  println(novel.copy(2001))
  println(novel.isWrittenBy(author))
  println(novel.isWrittenBy(imposter))

  val counter = new Counter()
  counter.increment.print
  counter.increment.increment.increment.print
  counter.increment(10).print

}

  //constructor
class Person(name:String, val age:Int=0) {
  //class params are not FIELDS (age) , make it val to make it fields

    val x=2
    println(1+3)

    def greet(name:String):Unit=  println(s"${this.name} says hi $name")

    //overloading
    def greet():Unit=  println(s"hi I am $name")

    //multiple constructors
    def this(name:String) =this(name,0)

    def this() = this("John")
  }




//Novel and writer
/*
writer: First name ,last name ,year  method fullname
Novel: name, year of release, author --> writer
method authorAge
isWrittenBy(author)
copy(new year release)= new instance of Novel


 */
class Writer(firstName:String, lastName:String, val year:Int) {
  def fullName(): String = firstName + " " + lastName


}

class Novel(name:String, yearOfRelease:Int, author:Writer){

  def authorAge= yearOfRelease - author.year

  def isWrittenBy(author:Writer) = author == this.author

  def copy(newYear:Int ):Novel = new Novel(name, newYear,author)
}

/*
counter class
input int val
method current count is returned
method to incre/decre by ==> new counter
overload  incre/decre by gven value
 */

class Counter(val count:Int= 0){

  def increment = {

    new Counter(count + 1) //immutability

  }
  def decrement = {
    println("decrementing")
    new Counter(count-1)
  }  //immutability

  def increment(n: Int ):Counter= {
    println("incrementing")
      if(n <=0) this
      else increment.increment(n - 1)
   // new Counter(count + n)

  }
  def decrement(n: Int ):Counter= // new Counter(count-n)
  {
    println("decrementing by "+n)
    if(n<=0) this
    else decrement.decrement(n-1)
  }

  def print = println(count)
}

