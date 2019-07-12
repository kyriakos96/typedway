package io.busyhive.typedway.common

import com.typesafe.config.Config

case class DbConfiguration(
    driver: String,
    url: String,
    user: String,
    password: String,
    port: Int
)

object DbConfiguration {
  def prepareConfiguration(config: Config): DbConfiguration = {
    val dbUser: String = config.getString("username")
    val dbPassword: String = config.getString("password")
    val dbDriver: String = config.getString("driver")
    val dbUrl: String = config.getString("url")
    val dbPort: Int = config.getInt("port")

    DbConfiguration(
      dbDriver,
      dbUrl,
      dbUser,
      dbPassword,
      dbPort
    )
  }
}
