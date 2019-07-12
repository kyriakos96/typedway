package io.busyhive.typedway.common

case class TypedwayCli(environment: String = "unknown",
                       action: String = "migrate")

object TypedwayCli {
  val argumentsParser =
    new scopt.OptionParser[TypedwayCli]("TypedwayMigrator") {
      head("Typedway migration job arguments")

      override def errorOnUnknownArgument: Boolean = true

      override def showUsageOnError = true

      opt[String]("environment")
        .required()
        .valueName("local")
        .action { (input, config) =>
          config.copy(environment = input)
        }
        .text("Environment")

    }
}
