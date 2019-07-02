package io.busyhive.typedway.utils

import java.io.File

object FileRetrieval {

  /**
    * Returns a sequence of all subdirectories directly under the passed dir
    * @param dir
    * @return Sequence of all subdirectories directly under the passed dir
    */
  def getListOfSubDirectories(dir: File): Seq[File] = {
    val files = dir.listFiles
    val dirs = for {
      file <- files
      if file.isDirectory
    } yield file
    dirs.toSeq
  }

  /**
    * Returns a sequence of files directly under the passed dir
    * @param dir
    * @return sequence of files directly under the passed dir
    */
  def getListOfFilesInDirectory(dir: String): Seq[File] = {
    val d = new File(dir)
    if (d.exists && d.isDirectory) {
      d.listFiles.filter(_.isFile).toSeq
    } else {
      Seq[File]()
    }
  }
}
