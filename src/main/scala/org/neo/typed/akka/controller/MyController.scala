package org.neo.typed.akka.controller


import akka.actor.typed._

import akka.util.Timeout
import org.neo.zio.model._
import sttp.tapir.server.akkahttp._
import sttp.tapir.{endpoint, stringBody, _}

import scala.concurrent._
import scala.concurrent.duration.DurationDouble
import akka.actor.typed.scaladsl.AskPattern._
import akka.http.scaladsl.server.Route

object MyController {
  private val testPath = endpoint.get.in("test").out(stringBody)

  def handleRequest(service: ActorSystem[Command]): Route = {
    implicit val timeout: Timeout = 0.5.seconds
    implicit val scheduler: Scheduler = service.scheduler
    implicit val ec: ExecutionContext = service.executionContext
    testPath.toRoute(_ => service.ask(GetTypeMessage.apply).mapTo[String].map(s => Right("Hello from a Scala controller! "+s)))
  }

}
