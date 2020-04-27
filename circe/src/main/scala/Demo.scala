import io.circe._, io.circe.generic.auto._, io.circe.parser._, io.circe.syntax._

sealed trait Foo
case class Bar(xs: Vector[String]) extends Foo
case class Qux(i: Int, d: Option[Double]) extends Foo

object Demo extends App {
  val foo: Foo = Qux(13, Some(14.0))

  val json = foo.asJson.noSpaces // Unsupported Scala 3 inline method deriveEncoder; found in trait io.circe.generic.AutoDerivation. // value asJson is not a member of Foo
  println(json)

  val decodedFoo = decode[Foo](json) // Unsupported Scala 3 inline method deriveDecoder; found in trait io.circe.generic.AutoDerivation.
  println(decodedFoo)
}
