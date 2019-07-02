package io.busyhive.typedway.migrationmanagement

import org.scalatest.{FlatSpec, Matchers}

class MigrationManagerSpec extends FlatSpec with Matchers {

  "The MigrationManager class" should "return the correct version" in {
    val version =
      MigrationManager("V1562078444__test", "some_schema", true).getVersion

    version shouldEqual "1562078444"
  }

}

object MigrationManagerSpec extends App {}
