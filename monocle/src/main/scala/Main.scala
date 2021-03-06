
// import monocle.syntax.all._
import monocle._


object Main extends App{


    case class Address(number: Int, street: String)

    case class User(name: String, address: Address)

    val user = User("Anna", Address(12, "high street"))

    // user.focus(_.name).replace("Bob")
    Focus.focus(user)(_.name).replace("Bob")
    // res: User = User("Bob", Address(12, "high street"))

    // user.focus(_.address.streetName).modify(_.toUpperCase)
    Focus.focus(user)(_.address.streetName).modify(_.toUpperCase)
    // res: User = User("Anna", Address(12, "HIGH STREET"))

    // user.focus(_.address.streetNumber).get
    Focus.focus(user)(_.address.streetNumber).get
    // res: Int = 12
}
