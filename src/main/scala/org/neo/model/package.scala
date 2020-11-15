package org.neo.zio

import akka.actor.typed.ActorRef

package object model {
  sealed trait Command
  final case class ServiceConfig(someKey: String = "Good Morning")
  case object GetMessage
  final case class GetTypeMessage(replyTo: ActorRef[String]) extends Command
}
