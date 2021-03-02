import org.log4s._
import package$package._

object Demo extends App {
  val data = 0.until(3).map(v => v.toString -> v * 10).toMap
  for {
    logger <- Seq(
      // getLogger,// usses macros so should fail, but other should work
      getLogger("named"), getLogger(this.getClass))
  } {
    logger.debug("Constructing new instance of MyClass")
    logger.trace(s"New instance's data set: $data")
    logger.warn(new RuntimeException("xyz"))("warn")
    logger.error("Failure")
  }
}
