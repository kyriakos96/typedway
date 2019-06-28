package io.busyhive.typedway

import org.scalatest._

class MigratorSpec extends FlatSpec with Matchers {
  "The Hello object" should "say hello" in {
    Migrator.greeting shouldEqual "hello"
  }
}
