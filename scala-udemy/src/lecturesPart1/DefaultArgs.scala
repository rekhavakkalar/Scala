package com.cisco.test
package lecturesPart1

object DefaultArgs extends  App{

  def tailRecFactorial(n:Int, accumulator:Int =1 ):Int=
    if(n <= 1) accumulator
    else tailRecFactorial(n-1,n * accumulator)

  val facto10 = tailRecFactorial(10)


  def savePicture(format:String = "jpg", width:Int=1950, height:Int= 1080):Unit= println("Saving picture")
  savePicture( width = 200,height = 500)
}
