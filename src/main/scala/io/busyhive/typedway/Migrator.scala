package io.busyhive.typedway

import io.busyhive.typedway.common.{Configuration, DbConfiguration, TypedwayCli}
import io.busyhive.typedway.migrationmanagement.MigrationManager
import slick.jdbc.MySQLProfile.api._
import slick.migration.api._
import slick.migration.api.flyway._
import com.typesafe.scalalogging.StrictLogging

class Migrator(args: TypedwayCli) extends Configuration with StrictLogging {

  override def environment: String = args.environment

  val dbConfig = DbConfiguration.prepareConfiguration(config)
  val db = Database.forURL(dbConfig.url, dbConfig.driver)

  def migrate: Unit = {
    implicit val infoProvider: MigrationInfo.Provider[Migration] =
      MigrationInfo.Provider.strict

    val migrations = MigrationManager.getAllMigrations

    val flyway =
      SlickFlyway(db)(migrations)
        .load()

    flyway.migrate()
  }

}

object Migrator extends App with StrictLogging {

  import io.busyhive.typedway.common.TypedwayCli._

  argumentsParser.parse(args, TypedwayCli()) match {
    case Some(arguments) =>
      logger.info(s"Arguments: $arguments")
      val typedwayMigrator = new Migrator(arguments)
      typedwayMigrator.migrate
    case None =>
      logger.error("No arguments provided")
      sys.error("No arguments provided")
  }

}
