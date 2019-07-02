package io.busyhive.typedway.migrationmanagement

import slick.migration.api.Migration

trait DbMigrations {
  def migration: Migration
}
