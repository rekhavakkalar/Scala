package com.cisco.test
package Excercies

object MyListAnonymous extends  App{

  val listofIntegers: MyListAnonym[Int] = new ConsAnonym(1, new ConsAnonym(2, new ConsAnonym(3, EmptyAnonym)))
  val CloneListofIntegers: MyListAnonym[Int] = new ConsAnonym(1, new ConsAnonym(2, new ConsAnonym(3, EmptyAnonym)))
  val anotherListofIntegers: MyListAnonym[Int] = new ConsAnonym(4, new ConsAnonym(5, EmptyAnonym))

  println(listofIntegers.map(elem => elem * 2).toString)

  println(listofIntegers.filter(elem => (elem % 2 == 0)).toString)


  println("Concatenated result : " + (listofIntegers ++ anotherListofIntegers).toString)

  println(listofIntegers.flatMap(elem => new ConsAnonym(elem, new ConsAnonym(elem + 1, EmptyAnonym))).toString)

  //case class
  println(CloneListofIntegers== listofIntegers)



}


abstract class MyListAnonym[+A] {
  def head: A
  def tail: MyListAnonym[A]
  def isEmpty: Boolean
  def printElement: String


  override def toString: String = "[" + printElement + "]"
  def ++[B >: A](list: MyListAnonym[B]): MyListAnonym[B]

  //higher order functions
  def map[B](transformer: A => B): MyListAnonym[B]
  def flatMap[B](transformer: A => MyListAnonym[B]): MyListAnonym[B]
  def filter(predicate: A => Boolean): MyListAnonym[A]



}

case object EmptyAnonym extends MyListAnonym[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: MyListAnonym[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  //polymorphic call
  override def printElement: String = ""
  def map[B](transformer: Nothing => B): MyListAnonym[B] = EmptyAnonym
  def flatMap[B](transformer:Nothing => MyListAnonym[B]): MyListAnonym[B] = EmptyAnonym
  def filter(predicate: Nothing => Boolean): MyListAnonym[Nothing] = EmptyAnonym
  override def ++[B >: Nothing](list: MyListAnonym[B]): MyListAnonym[B] = list
}

case class ConsAnonym[+A](h: A, t: MyListAnonym[A]) extends MyListAnonym[A] {
  def head: A = h
  def tail: MyListAnonym[A] = t
  def isEmpty: Boolean = false
  override def printElement: String = {
    if (t.isEmpty) " " + h
    else h + " " + t.printElement
  }
  def filter(predicate: A => Boolean): MyListAnonym[A] =
    if (predicate(h)) new ConsAnonym(h, t.filter(predicate))
    else t.filter(predicate)

  def map[B](transformer: A => B): MyListAnonym[B] =
    new ConsAnonym(transformer(h), t.map(transformer))
  override def ++[B >: A](list: MyListAnonym[B]): MyListAnonym[B] = new ConsAnonym(h, t ++ list)
  override def flatMap[B](transformer: A => MyListAnonym[B]): MyListAnonym[B] =
    transformer(h) ++ t.flatMap(transformer)
}
