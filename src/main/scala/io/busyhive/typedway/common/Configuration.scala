package io.busyhive.typedway.common

import com.typesafe.config.{Config, ConfigFactory}

trait Configuration {

  def environment: String

  @transient lazy val config: Config = ConfigFactory.load.getConfig(environment)

}
