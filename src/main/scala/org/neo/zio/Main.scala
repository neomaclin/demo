package org.neo.zio

import akka.actor.Props
import zio._
import zio.console._
import com.quasigroup.zio.akka.classic
import com.quasigroup.zio.akka.classic._
import com.quasigroup.zio.akka.classic.http.ToRoute
import com.quasigroup.zio.akka.models.BindOn
import org.neo.zio.model.ServiceConfig
import org.neo.zio.service.MyService


object Main extends App {

  def run(args: List[String]) = ( for{
    service <- register(Props(classOf[MyService], ServiceConfig()), "service")
    routes <- IO.effect[ToRoute](controller.handleRequest(service)(_))
    akka <- ZIO.access[ClassicAkka](_.get)
    _ <- classic.http.start(BindOn("127.0.0.1",8080),routes).provide(akka).use(_ => getStrLn)
  } yield {}).provideLayer(classic.demo ++ (classic.demo >>> classic.service) ++ Console.live).exitCode

}
