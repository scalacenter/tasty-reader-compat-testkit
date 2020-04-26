import verify._

object SomethingTest extends BasicTestSuite {

  test("addition") {
    assert(2 == 1 + 1) // Unsupported Scala 3 macro method apply; found in class verify.asserts.Recorder.
  }

  test("failing test") {
    case class Person(name: String = "Fred", age: Int = 42) {
      def say(words: String*) = words.mkString(" ")
    }
    assert(Person().say("ping", "poing") == "pong pong") // Unsupported Scala 3 macro method apply; found in class verify.asserts.Recorder.
  }

  test("should throw") {
    class DummyException extends RuntimeException("DUMMY")
    def test(): String = throw new DummyException

    intercept[DummyException] { // could not find implicit value for parameter pos: verify.sourcecode.SourceLocation
      test()
    }
  }

}
