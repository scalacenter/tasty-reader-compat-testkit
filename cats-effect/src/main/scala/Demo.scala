import cats.effect._

object Demo extends IOApp {
  def run(args: List[String]): IO[ExitCode] = {
    val isJS = 1.0.toString() == "1"
    val runningOn =
      if (isJS) IO.println("Running on JS")
      else IO.println("Running on the JVM")

    if (args.headOption.map(_ == "--do-it").getOrElse(false))
      (runningOn >> IO.println("I did it!")).as(ExitCode.Success)
    else
      (runningOn >> IO.println("Didn't do it")).as(if (isJS) ExitCode.Success else ExitCode(-1))
  }
}
