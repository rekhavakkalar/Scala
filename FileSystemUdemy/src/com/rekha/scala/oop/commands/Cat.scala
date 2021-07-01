package com.rekha.scala.oop.commands

import com.rekha.scala.oop.filesystem.State

class Cat(fileName:String) extends  Command {
  override def apply(state: State): State = {
    val wd = state.wd

    val dirEntry=wd.findEntry(fileName)
    if(dirEntry == null || !dirEntry.isFile) state.setMessage(fileName +" : No such file")
    else
      state.setMessage(dirEntry.asFile.contents)

  }
}
