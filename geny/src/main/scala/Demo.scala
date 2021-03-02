import geny.Generator

object Demo extends App {

    val a = Generator(0, 1, 2)

    println(a)

    class DummyCloseableSource{
        val iterator = Iterator(1, 2, 3, 4, 5, 6, 7, 8, 9)
        var closed = false
        def close() = {
          closed = true
        }
     }

     val g = Generator.selfClosing{
        val closeable = new DummyCloseableSource()
        (closeable.iterator, () => closeable.close())
     }

     val result = g.collectFirst{
         case a if a == 3 => a
     }

     println(result)
}
