import Dependencies._

ThisBuild / scalaVersion := ProjectLibraryVersion.scalaVersion
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "io.busyhive"
ThisBuild / organizationName := "busyhive"

lazy val root = (project in file("."))
  .settings(
    name := "typedway",
    resolvers += Resolver.jcenterRepo,
    libraryDependencies ++= Seq(
      scalaTest,
      flywayCore,
      mysqlJavaConnector,
      slick,
      slickMigrationApiFlyway,
      slf4jNop,
      slickHikaricp,
      scalaReflect,
      scoptLibrary,
      typesafeLoggingLibrary
    )
  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
