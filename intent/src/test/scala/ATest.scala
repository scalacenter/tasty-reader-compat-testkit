import intent._


class ATest extends TestSuite with Stateless {
  apply("3 + 3") {
    in("should be 6")(toEqual(expect(3 + 3))(6))
  }
}
