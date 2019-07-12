package io.busyhive.typedway.db.test_schema.migrations
import io.busyhive.typedway.migrationmanagement.DbMigrations
import slick.jdbc.MySQLProfile.api._
import slick.migration.api.{MySQLDialect, TableMigration}

case class Employee(employee_id: Long,
                    name: String,
                    birth_place: Option[String])

class Employees(tag: Tag)
    extends Table[Employee](tag, Some("test_schema"), "employees") {
  val employee_id = column[Long]("employee_id", O.PrimaryKey, O.AutoInc)
  val name = column[String]("name")
  val birth_place = column[Option[String]]("birth_place")

  override def * =
    (employee_id, name, birth_place) <> (Employee.tupled, Employee.unapply)

}

object V1561965022__employees extends DbMigrations {

  val employeesTable = TableQuery[Employees]

  implicit val dialect: MySQLDialect = new MySQLDialect

  override def migration =
    TableMigration(employeesTable).create
      .addColumns(_.employee_id, _.name, _.birth_place)
}
