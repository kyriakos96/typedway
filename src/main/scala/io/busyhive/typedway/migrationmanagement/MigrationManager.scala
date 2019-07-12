package io.busyhive.typedway.migrationmanagement

import java.io.File

import io.busyhive.typedway.utils.FileRetrieval
import slick.migration.api.Migration
import slick.migration.api.flyway.{MigrationInfo, VersionedMigration}

class MigrationManager(migrationFileName: String,
                       schema: String,
                       isMigration: Boolean) {

  val schemaPackages = "io.busyhive.typedway.db"

  val migrationLocation = isMigration match {
    case true =>
      s"$schemaPackages.$schema.migrations.$migrationFileName"
    case false =>
      s"$schemaPackages.$schema.repeatables.$migrationFileName"
  }

  def dbMigration: Migration = {

    import scala.reflect.runtime.universe
    val runtimeMirror = universe.runtimeMirror(getClass.getClassLoader)

    val reflectObject =
      runtimeMirror.reflectModule(runtimeMirror.staticModule(migrationLocation))

    reflectObject.instance.asInstanceOf[DbMigrations].migration
  }

  def getVersion: String = migrationFileName.substring(1, 11)

  override def toString = s"$migrationLocation"

}

object MigrationManager {

  def apply(migrationFileName: String,
            schema: String,
            isMigration: Boolean): MigrationManager =
    new MigrationManager(migrationFileName, schema, isMigration)

  def getAllMigrations: Seq[VersionedMigration[String]] = {
    val schemas = getMigrationSchemas
    val migrationFiles = getMigrationScripts(schemas)

    implicit val infoProvider: MigrationInfo.Provider[Migration] =
      MigrationInfo.Provider.strict

    migrationFiles.map(migrationFile =>
      VersionedMigration(migrationFile.getVersion, migrationFile.dbMigration))
  }

  private def getMigrationSchemas: Seq[File] = {
    val baseDir = "./src/main/scala/io/busyhive/typedway/db"
    FileRetrieval.getListOfSubDirectories(new File(baseDir))
  }

  private def getSchemaMigrationScripts(schema: File): Seq[MigrationManager] = {
    val schemaMigrationFiles =
      FileRetrieval.getListOfFilesInDirectory(s"${schema.getPath}/migrations")
    schemaMigrationFiles.map(
      migrationFile =>
        MigrationManager(migrationFile.getName.replace(".scala", ""),
                         schema.getName,
                         isMigration = true))
  }

  private def getMigrationScripts(schemas: Seq[File]): Seq[MigrationManager] =
    schemas.foldLeft(Seq[MigrationManager]()) { (acc, schema) =>
      acc ++ getSchemaMigrationScripts(schema)
    }
}
