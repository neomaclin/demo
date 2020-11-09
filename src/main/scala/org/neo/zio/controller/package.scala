package org.neo.zio

import akka.actor.{ActorRef, ActorSystem, Props}
import akka.http.scaladsl.server.Route
import com.quasigroup.zio.akka.classic.http.ToRoute
import com.quasigroup.zio.akka.classic.register
import org.neo.zio.model.ServiceConfig
import org.neo.zio.service.MyService
import zio.IO
import sttp.tapir._
import sttp.tapir.server.akkahttp._
import com.quasigroup.zio.akka.classic.http.ToRoute
import org.neo.zio.model.{GetMessage, ServiceConfig}
import org.neo.zio.service.MyService
import akka.pattern.ask
import akka.util.Timeout
import akka.util.Timeout

import scala.concurrent.duration.DurationDouble
import sttp.tapir.{endpoint, stringBody}

import scala.concurrent.ExecutionContext

package object controller {
  private val testPath = endpoint.get.in("test").out(stringBody)
  def handleRequest(service: ActorRef)(implicit ec:ExecutionContext): Route = {
    implicit val timeout: Timeout = 0.5.seconds
    testPath.toRoute(_ => (service ? GetMessage).mapTo[String].map(s => Right("Hello from a Scala controller! "+s)))
  }
}
