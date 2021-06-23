package com.cisco.test
package Excercies

import scala.annotation.tailrec

object TuplesAndMapExc extends  App{
  /*
  * 1. what would happen if i had 2 original entries "Jim" -> 555 and "JIM"-> 909
  *  phoneBook.map(pair => pair._1.toLowerCase -> pair._2)
  * 2. Overly simplified social network
  *    person = String
  *     -add Person to network
  *     -remove
  *     -friend(mutual)
  *     -unfriend(mutual)
  *     -stats a. number of friends of a person
  *            b. person with most friends
  *            c. how many people have no friends
  *            d. if there is a social connection between 2 ppl
  * */
  //1
  val  phoneBook = Map(("Jim", 555), "JIM" -> 222).withDefaultValue(-1)
  println(phoneBook.map(pair => pair._1.toLowerCase -> pair._2))  //Map(jim -> 222)


  //2
  def add(network:Map[String,Set[String]],person:String): Map[String,Set[String]]=
    network + (person -> Set())

  def friends(network:Map[String,Set[String]],a:String,b:String):Map[String,Set[String]]= {
    val friendsA = network(a)
    val friendsB = network(b)
    network+ (a -> (friendsA + b)) + (b-> (friendsB + a))
  }

  def unfriend(network:Map[String,Set[String]],a:String,b:String):Map[String,Set[String]]={
    val friendsA = network(a)
    val friendsB = network(b)
    network+ (a -> (friendsA - b)) + (b-> (friendsB - a))
  }

  def remove(network:Map[String,Set[String]],person:String):Map[String,Set[String]]={
    def removeaux(friends: Set[String],networkAcc:Map[String,Set[String]]):Map[String,Set[String]]=
      if (friends.isEmpty) networkAcc
      else removeaux(friends.tail,unfriend(networkAcc,person,friends.head))

    val unfriended = removeaux(network(person),network)
    unfriended - person
  }


  val empty :Map[String,Set[String]] = Map()

  val network = add (add(empty,"Bob"),"Mary")
    println(network)
    println(friends(network,"Bob","Mary"))
   println(unfriend(friends(network,"Bob","Mary"),"Bob","Mary"))
  println(remove(friends(network,"Bob","Mary"),"Bob"))


  //Jim,Bob,Mary
  val people = add(add (add(empty,"Bob"),"Mary"),"Jim")
  val jimBob = friends(people,"Bob","Jim")
  val testNetwork = friends(jimBob,"Bob","Mary")
  println(testNetwork)


  //stats
  def nFriends(network:Map[String,Set[String]], person:String):Int=
    if(!network.contains(person)) 0
    else network(person).size

  println(nFriends(testNetwork,"Bob"))

  def mostFriends(network:Map[String,Set[String]]):String=
    network.maxBy(pair => pair._2.size )._1

  println(mostFriends(testNetwork))

  def nPeopleWithNoFriends(network:Map[String,Set[String]]):Int=
        // network.filterKeys( k => network(k).isEmpty).size
  // or we can write as
       // network.filter( pair => pair._2.isEmpty).size
  // or we can write as
        //network.count(pair => pair._2.isEmpty)
  // or we can write as
    network.count(_._2.isEmpty)

  println(nPeopleWithNoFriends(testNetwork))

  def socialConnection(network:Map[String,Set[String]],a:String,b:String):Boolean= {
    @tailrec
    def bfs(target:String,consideredPpl:Set[String],disconvered:Set[String]):Boolean={
      if(disconvered.isEmpty) false
      else {
        val person = disconvered.head
        if(person==target) true
        else if(consideredPpl.contains(person)) bfs(target,consideredPpl,disconvered.tail)
        else bfs(target,consideredPpl+person,disconvered.tail ++ network(person))
      }
    }

    bfs(b,Set(),network(a) + a)
  }

  println(socialConnection(testNetwork,"Mary","Jim"))
  println(socialConnection(network,"Mary","Bob"))

}
