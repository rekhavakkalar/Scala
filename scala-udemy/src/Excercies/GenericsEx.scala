package com.cisco.test
package Excercies

object GenericsEx extends App {
  /*
  1. Generic traits MyPredicate[-T] with method[T] :Boolean
  2. Generic traits MyTransformer[-A,B] method to transform A to B type
  3.MyList:
  -map(MyTransformer)=>MyList
  -filter(predicate)=> MyList
  -flatMap(MyTransformer from A to MyList[B])=> MyList[B]

  class EvenPredicate extends MyPredicate[Int]
  class StringToIntTransformer extends MyTransformer [String, Int]


  [1,2,3].map(n * 2) => [2,4,6]
  [1,2,3,4].filter( n % 2)= [2,4]
  [1,2,3].flatMap( n => [n,n+1]) =>   [1,2,2,3,3,4]
  * */
  val listofIntegers: MyListEx[Int] = new ConsEx(1, new ConsEx(2, new ConsEx(3, EmptyEx)))
  val CloneListofIntegers: MyListEx[Int] = new ConsEx(1, new ConsEx(2, new ConsEx(3, EmptyEx)))
  val anotherListofIntegers: MyListEx[Int] = new ConsEx(4, new ConsEx(5, EmptyEx))
  println(listofIntegers.map(new MyTransformer[Int, Int] {
    override def transform(elem: Int): Int = elem * 2
  }).toString)

  println(listofIntegers.filter(new MyPredicate[Int] {
    override def test(elem: Int): Boolean = elem % 2 == 0
  }).toString)


  println("Concatenated result : " + (listofIntegers ++ anotherListofIntegers).toString)

  println(listofIntegers.flatMap(new MyTransformer[Int, MyListEx[Int]] {
    override def transform(elem: Int): MyListEx[Int] = new ConsEx(elem, new ConsEx(elem + 1, EmptyEx))
  }).toString)

  //case class
  println(CloneListofIntegers== listofIntegers)

}

abstract class MyListEx[+A] {
  def head: A
  def tail: MyListEx[A]
  def isEmpty: Boolean
  def printElement: String
  def map[B](transformer: MyTransformer[A, B]): MyListEx[B]
  def flatMap[B](transformer: MyTransformer[A, MyListEx[B]]): MyListEx[B]
  def filter(predicate: MyPredicate[A]): MyListEx[A]
  override def toString: String = "[" + printElement + "]"
  def ++[B >: A](list: MyListEx[B]): MyListEx[B]

}

case object EmptyEx extends MyListEx[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: MyListEx[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  //polymorphic call
  override def printElement: String = ""
  def map[B](transformer: MyTransformer[Nothing, B]): MyListEx[B] = EmptyEx
  def flatMap[B](transformer: MyTransformer[Nothing, MyListEx[B]]): MyListEx[B] = EmptyEx
  def filter(predicate: MyPredicate[Nothing]): MyListEx[Nothing] = EmptyEx
  override def ++[B >: Nothing](list: MyListEx[B]): MyListEx[B] = list
}

case class ConsEx[+A](h: A, t: MyListEx[A]) extends MyListEx[A] {
  def head: A = h
  def tail: MyListEx[A] = t
  def isEmpty: Boolean = false
  override def printElement: String = {
    if (t.isEmpty) " " + h
    else h + " " + t.printElement
  }
  def filter(predicate: MyPredicate[A]): MyListEx[A] =
    if (predicate.test(h)) new ConsEx(h, t.filter(predicate))
    else t.filter(predicate)

  def map[B](transformer: MyTransformer[A, B]): MyListEx[B] =
    new ConsEx(transformer.transform(h), t.map(transformer))
  override def ++[B >: A](list: MyListEx[B]): MyListEx[B] = new ConsEx(h, t ++ list)
  override def flatMap[B](transformer: MyTransformer[A, MyListEx[B]]): MyListEx[B] =
    transformer.transform(h) ++ t.flatMap(transformer)
}

trait MyPredicate[-T] {  //FunctionType T => Boolean
  def test(elem: T): Boolean
}

trait MyTransformer[-A, B] {  //FunctionType A => B
  def transform(elem: A): B
}