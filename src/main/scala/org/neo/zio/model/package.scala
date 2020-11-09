package org.neo.zio

package object model {
  final case class ServiceConfig(someKey: String = "Good Morning")
  case object GetMessage
}
