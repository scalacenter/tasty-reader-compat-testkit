package example

import zio.console.Console
import zio.ZIO
import java.io.IOException

object myconsole {
  def myPutStrLn(msg: String): ZIO[Console, Nothing, Unit] = ZIO.accessM(_.console.putStrLn(msg)) // uses extension method
  val myGetStrLn: ZIO[Console, IOException, String] = ZIO.accessM(_.console.getStrLn) // uses extension method
}
