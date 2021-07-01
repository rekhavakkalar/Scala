package com.rekha.scala.oop.commands

import com.rekha.scala.oop.filesystem.State

class PWD extends Command {
  override def apply(state: State): State = state.setMessage(state.wd.path)

}
