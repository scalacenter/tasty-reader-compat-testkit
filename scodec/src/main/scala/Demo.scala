import scodec._
import scodec.bits._
import scodec.codecs._

object Demo extends App {
  // Create a codec for an 8-bit unsigned int followed by an 8-bit unsigned int followed by a 16-bit unsigned int
  val firstCodec = uint8 :: uint8 :: uint16

  // Decode a bit vector using that codec
  val result: Attempt[DecodeResult[(Int, Int, Int)]] =
    firstCodec.decode(hex"102a03ff".bits)
  // Successful(DecodeResult(((16, 42), 1023), BitVector(empty)))

  // Sum the result
  val add3 = (_: Int) + (_: Int) + (_: Int)
  val sum: Attempt[DecodeResult[Int]] = result.map(_.map(add3))
  // Successful(DecodeResult(1081, BitVector(empty)))
}
