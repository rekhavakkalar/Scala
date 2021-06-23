package com.cisco.test
package Excercies

abstract  class MyList {
/*
* method  head --> 1st element
* method tail -> reminder of the list
* method isEmpty : boolean
* isThisListEmpty boolean
* add (int) --> new list with this element added
* toString() --> a string representation of the list
*
* */

  def head:Int
  def tail: MyList
  def isEmpty: Boolean
  def addElement(element:Int): MyList
    def printElement:String

  override def toString: String = "["+printElement+"]"
}


object  Empty extends  MyList{
   def head: Int = throw new NoSuchElementException
   def tail: MyList =  throw new NoSuchElementException
   def isEmpty: Boolean = true
   def addElement(element: Int): MyList = new Cons(element,Empty)
  //polymorphic call
  override def printElement: String = ""
}

class Cons(h:Int,t:MyList) extends  MyList{
   def head: Int = h
   def tail: MyList = t
   def isEmpty: Boolean = false
   def addElement(element: Int): MyList = new Cons(element,this)
  override def printElement: String = {
    if(t.isEmpty)" "+h
    else h + " " + t.printElement
  }
}

object  test extends  App{
  val list =new Cons(1,Empty)
  println(list.head)

  val list1 =new Cons(1,new Cons(2,new Cons(3, Empty)))
  println(list1.head)
  println(list1.tail)
  println(list1.addElement(4).head )
    println(list1.isEmpty)

  println(list1.toString)
}