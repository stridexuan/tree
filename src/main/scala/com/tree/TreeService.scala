package com.tree

import akka.actor.Actor
import spray.routing._
import spray.http._
import MediaTypes._

class TreeServiceActor extends Actor with TreeService {

  def actorRefFactory = context

  def receive = runRoute(myRoute)
}


// this trait defines our service behavior independently from the service actor
trait TreeService extends HttpService {

  val myRoute =
    path("") {
      get {
        respondWithMediaType(`text/html`) { // XML is marshalled to `text/xml` by default, so we simply override here
          complete {
            <html>
              <body>
                <h1>Say hello to <i>spray-routing</i> on <i>spray-can</i>!</h1>
              </body>
            </html>
          }
        }
      }
    }
}