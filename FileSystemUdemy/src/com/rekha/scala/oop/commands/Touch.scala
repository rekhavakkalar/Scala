package com.rekha.scala.oop.commands

import com.rekha.scala.oop.files.{DirEntry, File}
import com.rekha.scala.oop.filesystem.State

class Touch(name:String) extends  CreateEntry (name){
  override def createSpecificEntry(state: State): DirEntry =
    File.empty(state.wd.path,name)
}
