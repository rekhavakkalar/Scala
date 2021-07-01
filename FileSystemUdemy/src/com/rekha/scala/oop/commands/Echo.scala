package com.rekha.scala.oop.commands

import com.rekha.scala.oop.files.{Directory, File}
import com.rekha.scala.oop.filesystem.State

import scala.annotation.tailrec

class Echo(args:Array[String]) extends Command {
  override def apply(state: State): State = {
/*
if no args , then state
if just 1 arg ,print to console
if multiple args{
operator = next to last arg
if >
  echo to a file (may create if not there)
if >>
append to a file
else
echo everything to console
}
*/


    if(args.isEmpty) state
    else if(args.length == 1) state.setMessage(args(0))
    else {
      val operator = args(args.length-2)
      val fileName = args(args.length-1)
      val content = createContent(args,args.length-2)

      if(">>".equals(operator))
        doEcho(state,content,fileName, append=true)
      else if(">".equals(operator))
        doEcho(state,content,fileName, append=false)
      else
         state.setMessage(createContent(args,args.length))
    }
  }

  def getRootAfterEcho(currDirectory:Directory,path:List[String],contents:String,
                       append:Boolean): Directory ={
    /*
    if path is empty then fail (return curr directory)
    else if there are no more things to explore = path.tail.isEmpty
        find the file to create or add content to
        if file not found , then create file
        else if the entry is actually a directory , then fail
        else replace /append content to the file
             replace the entry with the filename with the NEW file

     else
          find the next dir to navigate
          call getRootAfterEcho recursively

          if recursive call fail , then fail
          else replace entry with the new directory , after the recurs call
    * */

    if(path.isEmpty) currDirectory
    else if(path.tail.isEmpty) {
      val dirEntry = currDirectory.findEntry(path.head)
      if(dirEntry == null)
          currDirectory.addEntry(new File(currDirectory.path,path.head,contents))
      else if(dirEntry.isDirectory) currDirectory
      else
        if(append)
          currDirectory.replaceEntry(path.head, dirEntry.asFile.appendContents(contents))
        else
          currDirectory.replaceEntry(path.head, dirEntry.asFile.setContents(contents))

    }
    else {
      val nextDir = currDirectory.findEntry(path.head).asDirectory //since not supporting relative path yet
      val newNextDir = getRootAfterEcho(nextDir,path.tail,contents,append)
      if(newNextDir == nextDir) currDirectory

      else currDirectory.replaceEntry(path.head,newNextDir)
    }
  }

  def doEcho(state: State, contents: String, fileName: String, append:Boolean):State = {
      if(fileName.contains(Directory.SEPARATOR))
          state.setMessage("Echo: Filename must not contain separators")
      else {
        val newRoot :Directory = getRootAfterEcho(state.root,state.wd.getAllFoldersInPath :+ fileName,contents,append)
        if(newRoot == state.root)
          state.setMessage(s"$fileName : no such file")
        else
          State(newRoot,newRoot.findDescendant(state.wd.getAllFoldersInPath))
      }
  }
//topIndex is non-inclusive
  def createContent(args:Array[String],topIndex:Int):String = {
    @tailrec
    def createContentHelper(currIndex:Int,accumulator:String):String={
      if(currIndex>=topIndex) accumulator
      else createContentHelper(currIndex+1,accumulator + " " + args(currIndex))
    }

    createContentHelper(0,"")

  }
}
