import verify._

object SomethingTest extends BasicTestSuite {

  test("addition") {
    assert(2 == 1 + 1)
  }

  test("failing test") {
    case class Person(name: String = "Fred", age: Int = 42) {
      def say(words: String*) = words.mkString(" ")
    }
    assert(Person().say("ping", "poing") == "pong pong")
  }

  test("should throw") {
    class DummyException extends RuntimeException("DUMMY")
    def test(): String = throw new DummyException

    intercept[DummyException] {
      test()
    }
  }

}
