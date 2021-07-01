package com.rekha.scala.oop.commands

import com.rekha.scala.oop.filesystem.State

class UnknownCommand extends  Command {
  override def apply(state: State): State =
    state.setMessage("Command not found")
}
