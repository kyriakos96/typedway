package io.busyhive.typedway

import slick.jdbc.MySQLProfile.api._
import slick.migration.api._
import slick.migration.api.flyway._

//import scala.concurrent.ExecutionContext.Implicits.global

object Migrator extends Greeting with App {
  println(greeting)

  val db = Database.forConfig("mysql")

  class TestTable(tag: Tag) extends Table[(Int, Int)](tag, "testtable") {
    val col1 = column[Int]("col1")
    val col2 = column[Int]("col2")
    def * = (col1, col2)
  }
  val testTable = TableQuery[TestTable]

  implicit val dialect: MySQLDialect = new MySQLDialect

  val m1 = TableMigration(testTable).create
    .addColumns(_.col1, _.col2)

  val m2 = SqlMigration("insert into testtable (col1, col2) values (2, 2)")

  implicit val infoProvider: MigrationInfo.Provider[Migration] =
    MigrationInfo.Provider.strict

  val migration = VersionedMigration("1", m1 & m2)

  val flyway =
    SlickFlyway(db)(Seq(migration))
      .load()

  flyway.migrate()

}

trait Greeting {
  lazy val greeting: String = "hello"
}
