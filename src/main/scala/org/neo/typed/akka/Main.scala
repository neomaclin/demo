package org.neo.typed.akka

import com.quasigroup.zio.akka.models.BindOn
import com.quasigroup.zio.akka.typed
import com.quasigroup.zio.akka.typed.http.ToRoute
import org.neo.typed.akka.controller.MyController
import org.neo.typed.akka.service.MyService
import org.neo.zio.model.{Command, ServiceConfig}
import zio._
import zio.console._


object Main extends App {

  def run(args: List[String]): URIO[Any with Console, ExitCode] = (for {
    root <- ZIO.succeed(MyService(ServiceConfig()))
    routes <- IO.effect[ToRoute[Command]](MyController.handleRequest)
    _ <- (for {
        akka <- typed.start[Command](root,"service")
        b <- typed.http.start(BindOn("127.0.0.1", 8080),routes).provide(akka)
      } yield b).use(_ => getStrLn)
  } yield {}).provideLayer(Console.live).exitCode

}