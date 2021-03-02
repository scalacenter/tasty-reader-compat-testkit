import sourcecode.{File, Line}

object Main {
  def log(foo: String)(implicit line: sourcecode.Line, file: sourcecode.File) = {
    println(s"${file.value}:${line.value} $foo")
  }

  log("Foooooo")
}