package com.tree.common.database

import com.tree.common.config.KakaxiConfig
import org.h2.tools.Server


/**
  * Created by hasee on 2016/11/5.
  */
object H2Database {
      private var server: Server = null;
      private var webServer: Server = null;

      def start(): Unit = {
            try {
                  Server.shutdownTcpServer(s"tcp://${KakaxiConfig.h2DatabaseHost}:${KakaxiConfig.h2DatabaseTcpPort}", "", true, true)
            } catch {
                  case e: Throwable =>
            }
            try {
                  server = Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", KakaxiConfig.h2DatabaseTcpPort.toString).start()
                  println("server status: " + server.getStatus)
            } catch {
                  case e: Throwable => println(e.getMessage, e)
            }
      }

      def startWebConsole(openBrowser: Boolean = false): Unit = {
            try {
                  if (webServer == null) {
                        webServer = Server.createWebServer("-web", "-webAllowOthers", "-webPort", KakaxiConfig.h2DatabaseWebPort.toString).start()
                  }
                  println("webServerStatus" + webServer.getStatus)

                  if (openBrowser) Server.openBrowser(s"http://${KakaxiConfig.h2DatabaseHost}:${KakaxiConfig.h2DatabaseWebPort}")
            } catch {
                  case e: Throwable => println(e.getMessage, e)
            }
      }

      def stop(): Unit = {
            def shutdownServer(server: Server): Unit = {
                  try {
                        server match {
                              case s if s != null => s.stop()
                              case _ => println("H2 server did not start")
                        }
                  } catch {
                        case e: Throwable => println(e.getMessage, e)
                  }
            }

            shutdownServer(server)
            shutdownServer(webServer)
            try{
                  Server.shutdownTcpServer(s"tcp://${KakaxiConfig.h2DatabaseHost}:${KakaxiConfig.h2DatabaseTcpPort}","",true,true)
                  println("serverStatus: "+server.getStatus)
            }catch {
                  case e:Throwable => println(e.getMessage,e)
            }
      }
}

/*object H2Test extends App {
      H2Database.start()
      Thread.sleep(10000)
      H2Database.stop()
}*/
