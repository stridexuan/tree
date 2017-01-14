package com.tree.common.config

import com.tree.common.database.{SparkDatabaseInfo, MysqlDatabaseInfo, DatabaseConfig}

import scala.util.{Properties, Try}

/**
  * Created by hasee on 2016/11/5.
  */
object KakaxiConfig extends CommonConfig{
    val dir = "../config"
  configHome = Properties.envOrElse("CONFIG_HOME",dir)
  setConfigFiles("serviceaddress.properties","serviceaddress.conf")

  def getConfigHome: String = {
    configHome
  }


  DatabaseConfig.mysql = MysqlDatabaseInfo(
    //host = config.getString("mysql.host"),
    port = 3366,
    //port = config.getInt("mysql.port"),
    user = config.getString("mysql.user"),
    password = config.getString("mysql.password"),
    driver = config.getString("mysql.driver")
  )

  DatabaseConfig.spark = SparkDatabaseInfo(
    host = config.getString("spark.host"),
    port = config.getInt("spark.port"),
    dbName = config.getString("spark.dbname"),
    user = config.getString("spark.user"),
    password = config.getString("spark.password"),
    driver = config.getString("spark.driver"),
    dbConfigs = List("spark.sql.thriftserver.scheduler.pool=" + Try(config.getString("spark.scheduler.pool.query")).getOrElse("default"))
  )

  println("spark:  " + DatabaseConfig.spark)

  lazy val h2DatabaseHost = config.getString("h2DatabaseHost")

  lazy val h2DatabaseTcpPort = config.getInt("h2DatabaseTcpPort")

  lazy val h2DatabaseWebPort = config.getInt("h2DatabaseWebPort")
}
