package org.neo.zio.service

import akka.actor.Actor
import org.neo.zio.model._

class MyService(serviceConfig: ServiceConfig) extends Actor {
  override def receive: Receive = {
    case GetMessage => sender() ! s"The service says: '${serviceConfig.someKey}'"
  }
}
