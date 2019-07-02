package io.busyhive.typedway.migrationmanagement

import slick.migration.api.Migration

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

}
