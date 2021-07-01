package com.rekha.scala.oop.commands

import com.rekha.scala.oop.files.{DirEntry, Directory}
import com.rekha.scala.oop.filesystem.State

abstract class CreateEntry(name:String) extends  Command {

  override  def apply(state:State) :State = {
    val wd = state.wd
    if(wd.hasEntry(name))
      state.setMessage(s"Entry $name already exist!")
    else if (name.contains(Directory.SEPARATOR)) {
      //mkdir something/somethingElse
      state.setMessage(s"$name must not contain separator")
    }else if(checkIlleagal(name))
      state.setMessage(s"$name illegal entry name")
    else {
      doCreateEntry(state, name)
    }

  }

  def checkIlleagal(name:String):Boolean= {
    name.contains(".")
  }

  def doCreateEntry(state:State, name:String):State ={

    def updateStructure(currentDirectory: Directory, path: List[String], newEntry: DirEntry) :Directory =
    {

      if(path.isEmpty) currentDirectory.addEntry(newEntry)
      else {
        /*
        * current Dir is = /a
        * path = ["b"]
        */

        println(path)
        println(path.head)
        println(path.head.isEmpty)
        val oldEntry = currentDirectory.findEntry(path.head).asDirectory
        currentDirectory.replaceEntry(oldEntry.name,updateStructure(oldEntry,path.tail,newEntry))
      }
      /*
      someDir
      /a
      /b
      (new Directory) /d

      => new someDir
      /a
      /b
      /d
      Ex for some complex root
      /a/b
        /c
        /d
        (new) /e
        so for this we have to :
        new /a
        new /b (parent /a)
          /c
          /d
          /e
      */


    }

    val wd = state.wd

    //1. all the directories in the full path
    val allDirsInPath = wd.getAllFoldersInPath
    //2. create new directory entry in the wd
   // val newDir  = Directory.empty(wd.path,name)
    val newEntry:DirEntry = createSpecificEntry(state)
    //3. update whole directory structure starting from root
    //(Directory structure is immutable)
    val newRoot = updateStructure(state.root,allDirsInPath, newEntry)
    //4. find new wd instance given wd is full path in the new directory structure
    val newWd = newRoot.findDescendant(allDirsInPath)

    State(newRoot,newWd)
  }

  def createSpecificEntry(state:State):DirEntry

}
