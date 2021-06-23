package com.cisco.test
package Part3FunctionalProgramming.Collections

object TuplesAndMaps extends  App {
  //tuples = finite ordered "lists"
  // val tuple = new Tuple2(2,"Hello Scala")  //(Int,String)
  //or
  val tuple = (2, "Hello Scala")
  println(tuple._1)
  println(tuple.copy(_2 = "good bye java"))
  println(tuple.swap) //"Hello Scala" ,2

  //maps associate key to value
  val aMap: Map[String, Int] = Map()
  val phoneBook = Map(("Jim", 555), "Daniel" -> 222).withDefaultValue(-1)
  //a -> b sytactic sugar for (a,b)

  println(phoneBook)

  //map operation
  println(phoneBook.contains("Jim"))
  println(phoneBook("Jim"))
  println(phoneBook("Mary")) //NoSuchElementException so add withDefaultValue(-1)

  //add a pairing
  val newPairing = "Mary" -> 345
  val newPhoneBook = phoneBook + newPairing   //maps are immutable
  println(newPhoneBook)  //Map(Jim -> 555, Daniel -> 222, Mary -> 345)


  //functions on map
  //map,filter,flatMap
  println(phoneBook.map(pair => pair._1.toLowerCase -> pair._2)) //Map(jim -> 555, daniel -> 222)


  //filterkeys
  println(phoneBook.filterKeys(x => x.startsWith("J"))) //Map(Jim -> 555)

  //mapValues
  println(phoneBook.mapValues(number => "080-"+ number )) //expected : Map(Jim -> 080-555, Daniel -> 080-222)

  //conversion
  println(phoneBook.toList)  //List((Jim,555), (Daniel,222))
  println(List(("Daniel",555)).toMap)  //Map(Daniel -> 555)

  val names = List("Bob", "Angela" ,"James","Mary","Jim")
  println(names.groupBy(name => name.charAt(0)))
}
