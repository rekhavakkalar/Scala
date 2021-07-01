package com.rekha.scala.oop.files

import com.rekha.scala.oop.filesystem.FileSystemException

class File(override val parentPath:String, override val name:String ,val contents:String) extends
DirEntry(parentPath,name) {
  override def asDirectory: Directory =
    throw new FileSystemException("File cannot be converted to a directory")

  override def getType: String = "File"
  override def isDirectory: Boolean = false

  override def isFile: Boolean = true

  override def asFile: File = this

  def setContents(newContent:String):File = new File(parentPath,name,newContent)
  def appendContents(newContents:String):File = setContents(contents + "\n" +newContents )
}

object File{
  def empty(parentPath:String, name:String):File=
    new File(parentPath,name,"")
}