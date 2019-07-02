package io.busyhive.typedway.utils

import java.io.File

import org.scalatest._

class FileRetrievalSpec extends FlatSpec with Matchers {

  val dir = "./src/main/scala/io/busyhive/typedway"

  "The FileRetrieval object" should "return subdirectories of the src dir when getListOfSubDirectories is called" in {
    val result = FileRetrieval.getListOfSubDirectories(new File(dir))
    result.size should be > 0
  }

  it should "return files of the src dir when getListOfFilesInDirectory  is called" in {
    val result =
      FileRetrieval.getListOfFilesInDirectory(dir)
    result.size should be > 0
  }

}

object FileRetrievalSpec extends App {}
