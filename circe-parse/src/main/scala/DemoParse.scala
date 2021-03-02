import io.circe._, io.circe.parser._

object DemoParse extends App {
  val rawJson: String = """
  {
    "foo": "bar",
    "baz": 123,
    "list of stuff": [ 4, 5, 6 ]
  }
  """
  val Right(parseResult) = parse(rawJson)

  val cursor: HCursor = parseResult.hcursor

  val Right(stuff) = cursor.downField("list of stuff").as[Array[Int]]

  println(stuff.mkString("[", ",", "]"))
}
