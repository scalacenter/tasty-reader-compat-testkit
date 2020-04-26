class MySuite extends munit.FunSuite { // Unsupported Scala 3 macro method generate; found in trait munit.internal.MacroCompat.LocationMacro.
  test("hello") {
    val obtained = 42
    val expected = 43
    assertEquals(obtained, expected)
  }
}
