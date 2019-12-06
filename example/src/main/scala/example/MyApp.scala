package example

import zio._
import zio.console._

object MyApp extends App { // required republishing ZIO locally to make App an abstract class

  def run(args: List[String]): URIO[Console, Int] =
    myAppLogic.fold(_ => 1, _ => 0)

  val myAppLogic =
    for {
      _    <- putStrLn("Hello! What is your name?")
      name <- getStrLn
      _    <- putStrLn(s"Hello, ${name}, welcome to ZIO!")
    } yield ()

}
