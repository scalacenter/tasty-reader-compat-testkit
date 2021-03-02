import monocle.syntax.all._

object DemoParse extends App {
  val user = User("Anna", Address(12, "high street"))

  user.focus(_.name).replace("Bob")
  // res: User = User("Bob", Address(12, "high street"))

  user.focus(_.address.streetName).modify(_.toUpperCase)
  // res: User = User("Anna", Address(12, "HIGH STREET"))

  user.focus(_.address.streetNumber).get
  // res: Int = 12
}
