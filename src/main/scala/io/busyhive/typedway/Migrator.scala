package io.busyhive.typedway

import java.io.File

import io.busyhive.typedway.migrationmanagement.MigrationManager
import io.busyhive.typedway.utils.FileRetrieval
import slick.jdbc.MySQLProfile.api._
import slick.migration.api._
import slick.migration.api.flyway._

//import scala.concurrent.ExecutionContext.Implicits.global

object Migrator extends Greeting with App {
  println(greeting)

  val db = Database.forConfig("mysql")

//  val m2 = SqlMigration("insert into testtable (col1, col2) values (2, 2)")
//

  implicit val infoProvider: MigrationInfo.Provider[Migration] =
    MigrationInfo.Provider.strict

  // TODO: Iterate mirgation scripts

  val baseDir = "./src/main/scala/io/busyhive/typedway/db"
  val schemas = FileRetrieval.getListOfSubDirectories(new File(baseDir))

  val migrationFiles = schemas.foldLeft(Seq[MigrationManager]()) {
    (acc, schema) =>
      val schemaMigrationFiles =
        FileRetrieval.getListOfFilesInDirectory(s"${schema.getPath}/migrations")
      acc ++ schemaMigrationFiles.map(
        migrationFile =>
          MigrationManager(migrationFile.getName.replace(".scala", ""),
                           schema.getName,
                           true))

  }

  val migrations =
    migrationFiles.map(migrationFile =>
      VersionedMigration(migrationFile.getVersion, migrationFile.dbMigration))

  println(migrations)

  val flyway =
    SlickFlyway(db)(migrations)
      .load()

  flyway.migrate()

}

trait Greeting {
  lazy val greeting: String = "hello"
}
