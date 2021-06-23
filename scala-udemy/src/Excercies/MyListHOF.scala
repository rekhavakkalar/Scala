package com.cisco.test
package Excercies

object MyListHOF extends  App{

  val listofIntegers: MyListHOF[Int] = new ConsHOF(1, new ConsHOF(2, new ConsHOF(3, EmptyHOF)))
  val CloneListofIntegers: MyListHOF[Int] = new ConsHOF(1, new ConsHOF(2, new ConsHOF(3, EmptyHOF)))
  val anotherListofIntegers: MyListHOF[Int] = new ConsHOF(4, new ConsHOF(5, EmptyHOF))
  val listOfStrings:MyListHOF[String]= new ConsHOF("Hello",new ConsHOF("Scala",EmptyHOF))

  println(listofIntegers.map(elem => elem * 2).toString)

  println(listofIntegers.filter(elem => (elem % 2 == 0)).toString)


  println("Concatenated result : " + (listofIntegers ++ anotherListofIntegers).toString)

  println(listofIntegers.flatMap(elem => new ConsHOF(elem, new ConsHOF(elem + 1, EmptyHOF))).toString)

  //case class
  println(CloneListofIntegers== listofIntegers)
  print("For each: " )
  listofIntegers.forEach(println)

  println(listofIntegers.sort((x,y)=>y-x))

  println(anotherListofIntegers.zipWith[String ,String](listOfStrings, _+"-"+_ ))

  println(listofIntegers.fold(0)(_+_))

  //for-comprehension
  val combo1 = for {
    n <- listofIntegers
    string <- listOfStrings
  } yield n + "- "+ string

  println(combo1)
}


abstract class MyListHOF[+A] {
  def head: A
  def tail: MyListHOF[A]
  def isEmpty: Boolean
  def printElement: String


  override def toString: String = "[" + printElement + "]"
  def ++[B >: A](list: MyListHOF[B]): MyListHOF[B]

  //higher order functions
  def map[B](transformer: A => B): MyListHOF[B]
  def flatMap[B](transformer: A => MyListHOF[B]): MyListHOF[B]
  def filter(predicate: A => Boolean): MyListHOF[A]
//HOFs

  def forEach(f:A => Unit) : Unit
  def sort(compare: (A,A) => Int): MyListHOF[A]
  def zipWith[B,C](list: MyListHOF[B], zip:(A,B) =>C ):MyListHOF[C]
  def fold[B](start:B)(operator:(B,A) => B):B
}

case object EmptyHOF extends MyListHOF[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: MyListHOF[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  //polymorphic call
  override def printElement: String = ""
  def map[B](transformer: Nothing => B): MyListHOF[B] = EmptyHOF
  def flatMap[B](transformer:Nothing => MyListHOF[B]): MyListHOF[B] = EmptyHOF
  def filter(predicate: Nothing => Boolean): MyListHOF[Nothing] = EmptyHOF
  override def ++[B >: Nothing](list: MyListHOF[B]): MyListHOF[B] = list

  override def forEach(f: Nothing => Unit): Unit = ()

  override def sort(compare: (Nothing, Nothing) => Int): MyListHOF[Nothing] = EmptyHOF

  override def zipWith[B, C](list: MyListHOF[B], zip: (Nothing, B) => C): MyListHOF[C] =
    if(!list.isEmpty) throw new RuntimeException("List do not have the same length")
    else EmptyHOF

  override def fold[B](start: B)(operator: (B, Nothing) => B): B = start
}

case class ConsHOF[+A](h: A, t: MyListHOF[A]) extends MyListHOF[A] {
  def head: A = h

  def tail: MyListHOF[A] = t

  def isEmpty: Boolean = false

  override def printElement: String = {
    if (t.isEmpty) " " + h
    else h + " " + t.printElement
  }

  def filter(predicate: A => Boolean): MyListHOF[A] =
    if (predicate(h)) new ConsHOF(h, t.filter(predicate))
    else t.filter(predicate)

  def map[B](transformer: A => B): MyListHOF[B] =
    new ConsHOF(transformer(h), t.map(transformer))

  override def ++[B >: A](list: MyListHOF[B]): MyListHOF[B] = new ConsHOF(h, t ++ list)

  override def flatMap[B](transformer: A => MyListHOF[B]): MyListHOF[B] =
    transformer(h) ++ t.flatMap(transformer)

  override def forEach(f: A => Unit): Unit = {
    f(h)
    t.forEach(f)
  }

  override def sort(compare: (A, A) => Int): MyListHOF[A] = {
    def insert(x: A, sortedList: MyListHOF[A]): MyListHOF[A]= {
      if(sortedList.isEmpty) new ConsHOF(x, EmptyHOF)
      else if(compare(x,sortedList.head) <= 0) new ConsHOF(x,sortedList)
      else new ConsHOF(sortedList.head, insert(x, sortedList.tail))
    }

    val tailSort = t.sort(compare)
    insert(h,tailSort)
  }


  override def zipWith[B, C](list: MyListHOF[B], zip: (A, B) => C): MyListHOF[C] =
    if(list.isEmpty) throw new RuntimeException("list do not have same length")
    else new ConsHOF(zip(h,list.head), t.zipWith(list.tail,zip))

  /* [1,2,3].fold(0)(+) =
  [2,3].fold(1)(+)=
  [3].fold(3)(+)=
  [].fold(6)(+)=6

  * */
  override def fold[B](start: B)(operator: (B, A) => B): B = {
    t.fold(operator(start,h))(operator)
  }
}

