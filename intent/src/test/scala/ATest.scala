import intent._

class ATest extends TestSuite with Stateless { self =>
  import self.{stringExt => test}, test._
  implicit val pos: intent.macros.Position        = ??? // comment line for illegal macro
  implicit val Eq: intent.core.Eq[Int]            = ??? // comment line for ambiguous implicit
  implicit val format: intent.core.Formatter[Int] = ??? // comment line for ambiguous implicit
  test("3 + 3") {                                       // illegal union type intent.core.MapLike
    in("should be 6") {
      toEqual(expect(3 + 3))(6)
    }
  }
}
