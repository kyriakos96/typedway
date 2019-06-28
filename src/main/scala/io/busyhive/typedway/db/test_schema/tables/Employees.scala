package io.busyhive.typedway.db.test_schema.tables

import slick.jdbc.MySQLProfile.api._

case class Employee(employee_id: Long,
                    name: String,
                    birth_place: Option[String])

class Employees(tag: Tag) extends Table[Employee](tag, "employees") {
  val employee_id = column[Long]("employee_id", O.PrimaryKey, O.AutoInc)
  val name = column[String]("name")
  val birth_place = column[Option[String]](birth_place)

  override def * =
    (employee_id, name, birth_place) <> (Employee.tupled, Employee.unapply)

}
