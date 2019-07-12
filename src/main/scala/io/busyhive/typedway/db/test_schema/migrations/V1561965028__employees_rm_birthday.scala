package io.busyhive.typedway.db.test_schema.migrations

import io.busyhive.typedway.migrationmanagement.DbMigrations
import slick.jdbc.MySQLProfile.api._
import slick.migration.api.{MySQLDialect, TableMigration}

object V1561965028__employees_rm_birthday extends DbMigrations {

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

  val employeesTable = TableQuery[Employees]

  implicit val dialect: MySQLDialect = new MySQLDialect

  override def migration =
    TableMigration(employeesTable)
      .dropColumns(_.birth_place)
}
