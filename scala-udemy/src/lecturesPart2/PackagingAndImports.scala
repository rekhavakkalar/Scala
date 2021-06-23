package com.cisco.test
package lecturesPart2
//name aliasing
import PackageAndImportTest.{King, Queen => Princess}

import java.sql.{Date => sqlDate}
import java.util.Date

//import PackageAndImportTest.{King, Queen}

object PackagingAndImports extends App{
  //package object
  sayHello
  println(SOME_RANDOM_VAR)

  val prince = new King
  val princess = new Princess

  val date = new Date
  //1  . use fully qulified name
 // val sqldate = new java.sql.Date(2018,5,4)
 //2. alias
  val sqlAlias = new sqlDate(2018,5,4)

  //default imports
  //java.lang
  //scala  -Int, Nothing , Function
  //scala Pre-def
   //- println , ???
}
