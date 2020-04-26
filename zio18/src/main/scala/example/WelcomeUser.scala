package example

import zio.{App, ZIO, UIO, URIO}
import zio.console.Console

import myconsole.{output, input}

object myconsole {
  def output(msg: String) = ZIO.accessM[Console](_.get.putStrLn(msg)) // can't find type scala.AnyKind while unpickling type T; perhaps it is missing from the classpath.
  val input               = ZIO.accessM[Console](_.get.getStrLn) // Unsupported Scala 3 inline method tagFromTagMacro; found in object izumi.reflect.Tag.
}

object WelcomeUser extends App {

  def run(args: List[String]): URIO[Console, Int] =
    welcomeUser as 0 orElse UIO.succeed(1)

  val welcomeUser =
    output("Hello! What is your name?") *>
    input.flatMap(name =>
      output(s"Hello, $name, have a great day!"))

}
