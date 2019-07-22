package io.busyhive.typedway.db.test_schema.migrations

import io.busyhive.typedway.migrationmanagement.DbMigrations
import slick.migration.api.{MySQLDialect, SqlMigration}

object V1561965000__create_test_schema extends DbMigrations {

  implicit val dialect: MySQLDialect = new MySQLDialect

  override def migration =
    SqlMigration("CREATE SCHEMA test_schema")
}
