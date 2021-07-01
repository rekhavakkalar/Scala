package com.rekha.scala.oop.commands

import com.rekha.scala.oop.files.{DirEntry, Directory}
import com.rekha.scala.oop.filesystem.State

import scala.annotation.tailrec

class CD(dir:String) extends Command {
  override def apply(state: State): State = {
    /*
    cd /something/somethingelse/.../
    cd a/b/c --> relative to the current working directory
    Later
    cd .
    cd ..
    cd a/./.././a

    1. find root
    2. find the absolute path of the directory i want to CD to
    3. find the directory to cd ,given the path
    4. change the state, given the new directory
    */
    //1
    val root = state.root
    val wd = state.wd
    //2
    val absolutePath = {
      if (dir.startsWith(Directory.SEPARATOR)) dir
      else if (wd.isRoot) wd.path + dir
      else wd.path + Directory.SEPARATOR + dir
    }
      //3
      val destDirectory = doFindEntry(root,absolutePath)

    if(destDirectory ==null || !destDirectory.isDirectory)
      state.setMessage(dir +" : no such Directory")
    else
      State(root,destDirectory.asDirectory)
  }

  def doFindEntry(root:Directory, path:String) :DirEntry = {

    @tailrec
    def findEntryHelper(currentDirectory: Directory, path: List[String]) :DirEntry= {
      if(path.isEmpty || path.head.isEmpty) currentDirectory
      else if(path.tail.isEmpty) currentDirectory.findEntry(path.head)
      else {
        val nextDir = currentDirectory.findEntry(path.head)
        if(nextDir == null || !nextDir.isDirectory) null
        else findEntryHelper(nextDir.asDirectory,path.tail)
      }
    }

    @tailrec
    def collapseRelativeTokens(path:List[String], result:List[String]): List[String] = {
      /*
      /a/b = ["a","b"]
      path.isEmpty -> No
      * */
      if(path.isEmpty) result
      else if(".".equals(path.head)) collapseRelativeTokens(path.tail,result)
      else if(".."equals(path.head)) {
        if(result.isEmpty) null
        else collapseRelativeTokens(path.tail,result.tail)
      } else collapseRelativeTokens(path.tail,result:+ path.head)

    }
    //1. tokens
    val tokens:List[String] = path.substring(1).split(Directory.SEPARATOR).toList

    //1.a Eliminate/ collapse path
    /*
      /a/.  = ["a"]=> /a
      /a/b/./.  => ["a","b"]=> /a/b

      /a/.. => ["a", ".."] => []=> /
      /a/b/.. => ["a","b",".."] => ["a"]=> /a
    * */

    val newTokens = collapseRelativeTokens(tokens,List())
    //2.navigate to the correct entry
    if(newTokens == null) null
    else findEntryHelper(root,newTokens)

  }



}
