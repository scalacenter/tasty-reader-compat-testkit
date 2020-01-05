package example

import myconsole.{myPutStrLn, myGetStrLn}

import zio._
import zio.console.Console

object ZIOApp extends App { // App must be abstract class until traits share jvm backend (see https://github.com/lampepfl/dotty/issues/7328)

  def run(args: List[String]): URIO[Console, Int] =
    myAppLogic.fold(_ => 1, _ => 0)

  val myAppLogic =
    myPutStrLn("Hello! What is your name?") *>
    myGetStrLn >>= (name =>
    myPutStrLn(s"Hello, $name, welcome to ZIO!"))

}
