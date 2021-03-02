import sttp.client3._

object Demo extends App {
  val sort: Option[String] = None
  val query = "http language:scala"

// the `query` parameter is automatically url-encoded
// `sort` is removed, as the value is not defined
  val request = basicRequest.get(
    uri"https://api.github.com/search/repositories?q=$query&sort=$sort"
  )

  val backend = HttpURLConnectionBackend()
  val response = request.send(backend)

// response.header(...): Option[String]
  println(response.header("Content-Length"))

// response.body: by default read into an Either[String, String] to indicate failure or success
  println(response.body)
}
