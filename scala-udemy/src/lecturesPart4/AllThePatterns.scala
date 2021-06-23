package com.cisco.test
package lecturesPart4

import Excercies.{ConsHOF, EmptyHOF, MyListHOF}

object AllThePatterns extends  App {
  //1 -constants
  /*val x:Any= "Scala"
  val constants = x match {
    case 1 => "A number"
    case "Scala" => "The scala"
    case true => " the truth"
    case AllThePatterns => "A singleton object"

  }

  //2- Match anything
    //2.1 wild card
  val matchAnything = x match {
      case _ =>
    }
  //2.2 variable
  val matchVariable = x match {
    case something => s"I have found $something"
  }

  //3 -Tuple
  val tuple = (1,2)
  val matchTuple = tuple match {
    case (1,1) =>
    case (something,2)=> s"I have found $something"
  }

  val nestedTuple =(1,(2,3))
  val matchNetedTuple = nestedTuple match {
    case (_,(2,v)) =>
  }

  //4- case classes- constructor
  //PM can be nested with case classes
  val aList:MyListHOF[Int] = ConsHOF(1,ConsHOF(2,EmptyHOF))
  val matchaList = aList match {
    case EmptyHOF =>
    case ConsHOF(head,ConsHOF(subhead,subtail)) =>
  }


  //5 - list patterns
  val stdList = List(1,2,3,42)
  val stdListMatching = stdList match {
    case List(1,_,_,_) => //extractor -advanced
    case List(1,_*) => //List of arbitrary length - advanced  //vararg pattern (_*)
    case 1 ::List(_) => //infix pattern
    case List(1,2,3) :+ 42 => //infix pattern

  }


  //6- Type specifier
  val unknown :Any = 2
  val unknownMatch = unknown match {
    case list: List[Int] => //explicit type specifier
    case _ =>
  }

  //7- name binding
  val nameBindingMatch = aList match {
    case nonEmptyList @ ConsHOF(_, _) => //name binding --> name can be used later here
    case ConsHOF(1, rest @ ConsHOF(2, _) ) => ////name binding in nested patterns -->
  }

  //8- multi patterns
  val multipattern = aList match {
    case EmptyHOF| ConsHOF(0,_) => //compound pattern (multi pattern)
  }

  //9- if guards
  val secondElementSpl = aList match {
    case ConsHOF(_,ConsHOF(specialElement, _)) if specialElement % 2 == 0 =>
  }*//*val x:Any= "Scala"
  val constants = x match {
    case 1 => "A number"
    case "Scala" => "The scala"
    case true => " the truth"
    case AllThePatterns => "A singleton object"

  }

  //2- Match anything
    //2.1 wild card
  val matchAnything = x match {
      case _ =>
    }
  //2.2 variable
  val matchVariable = x match {
    case something => s"I have found $something"
  }

  //3 -Tuple
  val tuple = (1,2)
  val matchTuple = tuple match {
    case (1,1) =>
    case (something,2)=> s"I have found $something"
  }

  val nestedTuple =(1,(2,3))
  val matchNetedTuple = nestedTuple match {
    case (_,(2,v)) =>
  }

  //4- case classes- constructor
  //PM can be nested with case classes
  val aList:MyListHOF[Int] = ConsHOF(1,ConsHOF(2,EmptyHOF))
  val matchaList = aList match {
    case EmptyHOF =>
    case ConsHOF(head,ConsHOF(subhead,subtail)) =>
  }


  //5 - list patterns
  val stdList = List(1,2,3,42)
  val stdListMatching = stdList match {
    case List(1,_,_,_) => //extractor -advanced
    case List(1,_*) => //List of arbitrary length - advanced  //vararg pattern (_*)
    case 1 ::List(_) => //infix pattern
    case List(1,2,3) :+ 42 => //infix pattern

  }


  //6- Type specifier
  val unknown :Any = 2
  val unknownMatch = unknown match {
    case list: List[Int] => //explicit type specifier
    case _ =>
  }

  //7- name binding
  val nameBindingMatch = aList match {
    case nonEmptyList @ ConsHOF(_, _) => //name binding --> name can be used later here
    case ConsHOF(1, rest @ ConsHOF(2, _) ) => ////name binding in nested patterns -->
  }

  //8- multi patterns
  val multipattern = aList match {
    case EmptyHOF| ConsHOF(0,_) => //compound pattern (multi pattern)
  }

  //9- if guards
  val secondElementSpl = aList match {
    case ConsHOF(_,ConsHOF(specialElement, _)) if specialElement % 2 == 0 =>
  }*/

  //ALL

  /*QUestion*/
  val numbers = List(1,2,3)
  val numbersMatch = numbers match {
    case listOfString:List[String] => "list of String"
    case listOfIntegers: List[Int] =>  "list of numbers"
    case _ => " "
  }

  println(numbersMatch)  //list of String

  //JVM trick question
  //JVM will erase the generics in cases, so we will have case as  listOfString:List => "list of String"
  //this is called type-eraser

}
