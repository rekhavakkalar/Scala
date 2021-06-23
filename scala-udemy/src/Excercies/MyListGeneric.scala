package com.cisco.test
package Excercies

abstract  class MyListGeneric[+A] {
  def head:A
  def tail: MyListGeneric[A]
  def isEmpty: Boolean
  def addElement[B >: A](element:B): MyListGeneric[B]
  def printElement:String

  override def toString: String = "["+printElement+"]"
  }


  object Empty1 extends MyListGeneric [Nothing] {
    def head: Nothing = throw new NoSuchElementException
    def tail: MyListGeneric[Nothing] =  throw new NoSuchElementException
    def isEmpty: Boolean = true
    def addElement[B >: Nothing] (element: B): MyListGeneric[B] = new Cons1(element,Empty1)
    //polymorphic call
    override def printElement: String = ""
  }

  class Cons1[+A](h:A,t:MyListGeneric[A]) extends MyListGeneric[A] {
    def head: A = h
    def tail: MyListGeneric[A] = t
    def isEmpty: Boolean = false
    def addElement[B >: A](element: B): MyListGeneric[B] = new Cons1(element,this)
    override def printElement: String = {
      if(t.isEmpty)" "+h
      else h + " " + t.printElement
    }
  }

  object  testList extends  App{
    val listOfIntegers:  MyListGeneric[Int] = new Cons1(1, new Cons1(2,new Cons1(3, Empty1)))
    println(listOfIntegers.head)
    println(listOfIntegers.toString)
    val listOfStrings: MyListGeneric[String] = new Cons1("Hello", new Cons1("Scala", Empty1))

    println(listOfStrings.toString)

  }