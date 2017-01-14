package com.tree.common.database

/**
  * Created by hasee on 2016/11/5.
  */
object DatabaseConfig {
  var mysql:DatabaseInfo = null
  var spark:DatabaseInfo = null

}

case class DatabaseInfo(url: String,
                        user:String,
                        password:String,
                        driver:String,
                        dbName:String,
                        dbConfig:List[String]  = Nil
                       //for spark fair schedualer mode
)

object MysqlDatabaseInfo{
  def apply(host:String = "//localhost",
            port: Int = 3306,
            dbName:String = "test",
            user:String = "root",
            password:String = "root",
            driver:String = "com.mysql.jdbc.Driver"
           ):DatabaseInfo = {
    DatabaseInfo(s"jdbc:mysql:$host:$port/$dbName",user,password,driver,dbName)
  }
}

object SparkDatabaseInfo {
  def apply(host: String,
            port: Int = 10000,
            dbName: String = "default",
            user: String = "",
            password: String = "",
            driver: String = "org.apache.hive.jdbc.HiveDriver",
            dbConfigs:List[String] = SparkDefaultConfig.configs): DatabaseInfo = {
    DatabaseInfo(s"jdbc:hive2://$host:$port", user, password, driver, dbName, dbConfigs)
  }
}

object SparkDefaultConfig {
  val configs:List[String] = {
    List("spark.sql.thriftserver.scheduler.pool=default")
  }
}
