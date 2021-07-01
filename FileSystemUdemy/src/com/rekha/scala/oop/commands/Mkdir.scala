package com.rekha.scala.oop.commands

import com.rekha.scala.oop.files.{DirEntry, Directory}
import com.rekha.scala.oop.filesystem.State

class Mkdir(name:String) extends  CreateEntry(name) {
  override def createSpecificEntry(state: State): DirEntry =
    Directory.empty(state.wd.path,name)
}
