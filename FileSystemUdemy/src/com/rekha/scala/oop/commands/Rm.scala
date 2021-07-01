package com.rekha.scala.oop.commands

import com.rekha.scala.oop.files.Directory
import com.rekha.scala.oop.filesystem.State

class Rm(name:String) extends Command {
  override def apply(state: State): State = {
    //1. get wd
    val wd = state.wd
    //2. get absolute path
    val absPath = {
      if(name.startsWith(Directory.SEPARATOR)) name
      else if(wd.isRoot) wd.path+name
      else wd.path+Directory.SEPARATOR+name
    }
    //3. do some checks
    if(Directory.ROOT_PATH.equals(absPath))
      state.setMessage("THis is not supported yet")
    else
      doRm(state, absPath)
  }


  def doRm(state: State, path: String):State = {
    //4. find entry to remove
    //5. update struct like for mkdir
    def rmHelper(currentDirectory: Directory, path: List[String]): Directory ={
        if(path.isEmpty) currentDirectory
        else if(path.tail.isEmpty)  currentDirectory.removeEntry(path.head)
        else {
          val nextDir = currentDirectory.findEntry(path.head)
          if (!nextDir.isDirectory) currentDirectory
          else {
            val newNextDirectory = rmHelper(nextDir.asDirectory,path.tail)
            if(newNextDirectory == nextDir ) currentDirectory
            else currentDirectory.replaceEntry(path.head, newNextDirectory)
          }
        }
    }
    val tokens = path.substring(1).split(Directory.SEPARATOR).toList
    val newRoot:Directory = rmHelper(state.root,tokens)

    if(newRoot== state.root) state.setMessage(path +": no such file or directory")
    else State(newRoot,newRoot.findDescendant(state.wd.path.substring(1)))

  }
}
