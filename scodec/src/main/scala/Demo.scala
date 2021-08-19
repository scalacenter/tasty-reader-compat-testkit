import scodec._
import scodec.bits._
import scodec.codecs._
import scodec.codecs.`codecs$package`._

object Demo extends App with Syntaxes {

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


trait Syntaxes {

  implicit class HexStringSyntax(val sc: StringContext) {
    def hex(args: Any*): ByteVector = `Interpolators$package`.hex(sc)(args:_*)
  }

  implicit class consSyntax[B](val codecB: Codec[B]) {
    def ::[A](codecA: Codec[A])(implicit di: DummyImplicit): Codec[(A, B)] = Codec.::(codecA)(codecB)(di)
  }

  implicit class consSyntaxTuple[B, C](val codecBC: Codec[(B, C)]) {
    def ::[A](codecA: Codec[A]): Codec[(A, B, C)] = Codec.::(codecA)(codecBC)
  }

}
