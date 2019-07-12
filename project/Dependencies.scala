import sbt._

object Dependencies {
  lazy val scalaTest = "org.scalatest" %% "scalatest" % ProjectLibraryVersion.scalaTestVersion % Test
  lazy val flywayCore = "org.flywaydb" % "flyway-core" % ProjectLibraryVersion.flywayCoreVersion
  lazy val mysqlJavaConnector = "mysql" % "mysql-connector-java" % ProjectLibraryVersion.mysqlConnectorVersion
  lazy val slickMigrationApiFlyway = "io.github.nafg" %% "slick-migration-api-flyway" % ProjectLibraryVersion.slickMigrationApiFlywayVersion
  lazy val slick = "com.typesafe.slick" %% "slick" % ProjectLibraryVersion.slickVersion
  lazy val slickCodeGenerator = "com.typesafe.slick" %% "slick-codegen_2.11" % ProjectLibraryVersion.slickVersion
  lazy val slf4jNop = "org.slf4j" % "slf4j-nop" % ProjectLibraryVersion.slf4jNopVersion
  lazy val slickHikaricp = "com.typesafe.slick" %% "slick-hikaricp" % ProjectLibraryVersion.slickHikaricpVersion
  lazy val scalaReflect = "org.scala-lang" % "scala-reflect" % ProjectLibraryVersion.scalaVersion
  lazy val scoptLibrary = "com.github.scopt" %% "scopt" % ProjectLibraryVersion.scoptVersion
  lazy val typesafeLoggingLibrary = "com.typesafe.scala-logging" %% "scala-logging" % ProjectLibraryVersion.typesafeLoggingVersion
}
