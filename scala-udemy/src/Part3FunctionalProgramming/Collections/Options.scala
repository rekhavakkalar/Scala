package com.cisco.test
package Part3FunctionalProgramming.Collections

import javax.xml.stream.events.Comment
import scala.util.Random

object Options extends  App {
  val myFirstOption :Option[Int] = Some(3)
  val noOption :Option[Int] = None
  println(myFirstOption)
  println(noOption)

  def unsafeMethod():String = null
  //val result =Some(unsafeMethod()) //wrong!!!!! Some cannot accept null
  val result = Option(unsafeMethod())
  println(result)


  //chained methods
  def backupMethods():String = "A valid result"
  val chainedResult = Option(unsafeMethod()).orElse(  Option(backupMethods()))
  println(chainedResult)


  def betterUnsafeMethod():Option[String]= None
  def betterBackUpMethod():Option[String]= Some("A Valid result")
  val betterResult = betterUnsafeMethod() orElse betterBackUpMethod()
  println(betterResult)


  //functions on Options
  println(myFirstOption.isEmpty)
  println(myFirstOption.get)  //Unsafe may throw null pointer exception -DO NOT USE THIS

  //map,flatmap,filter
  println(myFirstOption.map(_ * 2))
  println(myFirstOption.filter(x => x> 10))
  println(myFirstOption.flatMap(x => Option(x * 10)))


  //for-comprehensions

  /*
  * 1. val config :Map[String,String]
  * */
  val config :Map[String,String] = Map(
    //fetched from elsewhere
    "host" -> "10.10.0.2",
    "port" -> "2020"
  )

  class Connection{
    def connect ="connected"
  }

  object Connection{
    val random = new Random(System.nanoTime())
    def apply(host:String,port:String):Option[Connection]=
      if (random.nextBoolean()) Some(new Connection)
      else None
  }


  //try to establish a connection
  //if you can , print the connect method

  val host = config.get("host")
  val port = config.get("port")

  /*
  * if (h!=null)
  * if(p!=null)
  *   return Connection.apply(h,p)
  *
  * return null
  * */
  val connection = host.flatMap(h => port.flatMap(p => Connection.apply(h,p)))

  /*
  * if(c!= null)
  *   c.connect
  * else null
  * */
  val connectionStatus= connection.map(c => c.connect)
  /*
  * if(connectionStatus == null) None
  * else print(Some(connectionStatus.get))
  * */
  println(connectionStatus)

  /*
  * if(connectionStatus!=null) println (status)
  *
  * */
  connectionStatus.foreach(println)

//chained calls
println("Chained : ")
  config.get("host").
    flatMap(host => config.get("port").
    flatMap(port => Connection(host,port)).
    map(connection => connection.connect)).
    foreach(println)



  //for-comprehensions
println("for-comprehensions:  : ")
  val connectionStat = for{
    host <- config.get("host")
    port <- config.get("port")
    connection <- Connection(host,port)
  } yield connection.connect

  connectionStat.foreach(println)
}
