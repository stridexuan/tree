package com.tree.common.database

import java.sql.{SQLException, DriverManager, Connection}

/**
  * Created by hasee on 2016/11/6.
  */
trait ConnectionFactory {
  def configInfo: DatabaseInfo

  def createConnection: Connection = {
    try {
      Class.forName(configInfo.driver)
      DriverManager.setLoginTimeout(0)
      DriverManager.getConnection(configInfo.url, configInfo.user, configInfo.password)
    } catch {
      case e: SQLException => println(s"database connection failed! url:${configInfo.url}");null
      case e: Throwable => println(e);null
    }
  }
}

object ConnectionFactory{
  implicit object Mysql extends MysqlConnectionFactory
}

trait MysqlConnectionFactory extends ConnectionFactory{
  def configInfo: DatabaseInfo = DatabaseConfig.mysql
}
