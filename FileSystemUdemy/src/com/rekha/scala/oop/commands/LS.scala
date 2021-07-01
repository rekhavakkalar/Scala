package com.rekha.scala.oop.commands

import com.rekha.scala.oop.files.DirEntry
import com.rekha.scala.oop.filesystem.State

class LS extends  Command {
  override def apply(state: State): State = {

    val contents = state.wd.contents
    val niceOutput = createNiceOutput(contents)
    state.setMessage(niceOutput)

  }
  def createNiceOutput(contents:List[DirEntry]) : String = {
    if(contents.isEmpty) ""
    else {
      val entry = contents.head
      entry.name+"["+entry.getType+"]"+"\n"+createNiceOutput(contents.tail)
    }
  }
}
