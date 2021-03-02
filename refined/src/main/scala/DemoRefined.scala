import eu.timepit.refined._
import eu.timepit.refined.api.Refined
import eu.timepit.refined.auto._
import eu.timepit.refined.numeric._

object DemoRefined extends App {

  val i1: Int Refined Positive = 5
  println(i1)
  println(refineMV[Positive](5))

  val x = 42 // suppose the value of x is not known at compile-time
  assert(refineV[Positive](x).isRight)
  assert(refineV[Positive](-x).isLeft)
}
