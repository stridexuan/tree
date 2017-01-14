package com.tree.common.database

import java.sql.Connection

/**
  * Created by hasee on 2016/11/6.
  */
trait SparkConnectionFactory extends ConnectionFactory{
    def configInfo: DatabaseInfo = DatabaseConfig.spark
override  def createConnection: Connection = {
    val conn = super.createConnection
    if (conn != null){
      useDatabase(conn,configInfo.dbName)
    }
    conn
  }
protected def useDatabase(conn: Connection,database: String): Unit = {
  val stmt = conn.createStatement
  stmt.execute(s"use $database")
  configInfo.dbConfig.toList.foreach(x =>{
    stmt.execute(s"Set ${x.replace(" ","")}")
  })
}
}

class SparkConnectionFactoryWithEnvironment(implicit environments:List[String] = Nil) extends SparkConnectionFactory{
  protected override def useDatabase(conn: Connection, database: String): Unit = {
    val stmt = conn.createStatement
    println("Spark useDatabase =" + s"use $database")
    stmt.execute(s"use $database")
    (configInfo.dbConfig:::environments).foreach{x=>
      println(s"Spark SET ${x.replace(" ","")}")
      stmt.execute(s"SET ${x.replace(" ","")}")
    }
    stmt.close()
  }
}

