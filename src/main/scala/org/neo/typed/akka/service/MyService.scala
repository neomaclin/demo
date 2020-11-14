package org.neo.typed.akka.service

import akka.actor.typed._
import akka.actor.typed.scaladsl.Behaviors
import org.neo.zio.model._

object MyService {
  def apply(serviceConfig: ServiceConfig): Behavior[Command] =
    Behaviors.receiveMessage[Command]{ case GetTypeMessage(replyTo) =>
        replyTo ! s"The service says: '${serviceConfig.someKey}'"
        Behaviors.same
    }
}
