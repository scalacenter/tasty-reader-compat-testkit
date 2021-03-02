import cats.effect.{IO, IOApp}
import fs2.{Stream, text}
import fs2.io.file.Files
import java.nio.file.Paths

object Converter extends IOApp.Simple {

  val converter: Stream[IO, Unit] = {
    def fahrenheitToCelsius(f: Double): Double =
      (f - 32.0) * (5.0/9.0)

    Files[IO].readAll(Paths.get("testdata/fahrenheit.txt"), 4096)
      .through(text.utf8Decode)
      .through(text.lines)
      .filter(s => !s.trim.isEmpty && !s.startsWith("//"))
      .map(line => fahrenheitToCelsius(line.toDouble).toString)
      .intersperse("\n")
      .through(text.utf8Encode)
      .through(Files[IO].writeAll(Paths.get("testdata/celsius.txt")))
  }

  def run: IO[Unit] =
    converter.compile.drain
}
