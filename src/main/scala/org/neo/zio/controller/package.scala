package org.neo.zio

import akka.actor.ActorRef
import akka.http.scaladsl.server.Route
import akka.pattern.ask
import akka.util.Timeout
import org.neo.zio.model.GetMessage
import sttp.tapir.server.akkahttp._
import sttp.tapir.{endpoint, stringBody, _}

import scala.concurrent.ExecutionContext
import scala.concurrent.duration.DurationDouble

package object controller {
  private val testPath = endpoint.get.in("test").out(stringBody)
  def handleRequest(service: ActorRef)(implicit ec:ExecutionContext): Route = {
    implicit val timeout: Timeout = 0.5.seconds
    testPath.toRoute(_ => (service ? GetMessage).mapTo[String].map(s => Right("Hello from a Scala controller! "+s)))
  }
}
