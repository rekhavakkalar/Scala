package com.cisco.test
package Excercies

object MyListFunc extends  App{
  val listofIntegers: MyListFun[Int] = new ConsFun(1, new ConsFun(2, new ConsFun(3, EmptyFun)))
  val CloneListofIntegers: MyListFun[Int] = new ConsFun(1, new ConsFun(2, new ConsFun(3, EmptyFun)))
  val anotherListofIntegers: MyListFun[Int] = new ConsFun(4, new ConsFun(5, EmptyFun))
  println(listofIntegers.map(new Function1[Int, Int] {
    override def apply(elem: Int): Int = elem * 2
  }).toString)

  println(listofIntegers.filter(new Function1[Int,Boolean] {
    override def apply(elem: Int): Boolean = elem % 2 == 0
  }).toString)


  println("Concatenated result : " + (listofIntegers ++ anotherListofIntegers).toString)

  println(listofIntegers.flatMap(new Function1[Int, MyListFun[Int]] {
    override def apply(elem: Int): MyListFun[Int] = new ConsFun(elem, new ConsFun(elem + 1, EmptyFun))
  }).toString)

  //case class
  println(CloneListofIntegers== listofIntegers)



}


abstract class MyListFun[+A] {
  def head: A
  def tail: MyListFun[A]
  def isEmpty: Boolean
  def printElement: String


  override def toString: String = "[" + printElement + "]"
  def ++[B >: A](list: MyListFun[B]): MyListFun[B]

  //higher order functions
  def map[B](transformer: A => B): MyListFun[B]
  def flatMap[B](transformer: A => MyListFun[B]): MyListFun[B]
  def filter(predicate: A => Boolean): MyListFun[A]



}

case object EmptyFun extends MyListFun[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: MyListFun[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  //polymorphic call
  override def printElement: String = ""
  def map[B](transformer: Nothing => B): MyListFun[B] = EmptyFun
  def flatMap[B](transformer:Nothing => MyListFun[B]): MyListFun[B] = EmptyFun
  def filter(predicate: Nothing => Boolean): MyListFun[Nothing] = EmptyFun
  override def ++[B >: Nothing](list: MyListFun[B]): MyListFun[B] = list
}

case class ConsFun[+A](h: A, t: MyListFun[A]) extends MyListFun[A] {
  def head: A = h
  def tail: MyListFun[A] = t
  def isEmpty: Boolean = false
  override def printElement: String = {
    if (t.isEmpty) " " + h
    else h + " " + t.printElement
  }
  def filter(predicate: A => Boolean): MyListFun[A] =
    if (predicate(h)) new ConsFun(h, t.filter(predicate))
    else t.filter(predicate)

  def map[B](transformer: A => B): MyListFun[B] =
    new ConsFun(transformer(h), t.map(transformer))
  override def ++[B >: A](list: MyListFun[B]): MyListFun[B] = new ConsFun(h, t ++ list)
  override def flatMap[B](transformer: A => MyListFun[B]): MyListFun[B] =
    transformer(h) ++ t.flatMap(transformer)
}
