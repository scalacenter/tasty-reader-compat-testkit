import java.io.File
import com.github.tototoshi.csv._

object Demo extends App {
  val sample = "scala-csv/src/main/resources/sample.csv"
  val `with-headers` = "scala-csv/src/main/resources/with-headers.csv"

  {
    val reader = CSVReader.open(new File(sample))

    println(reader.all())
    reader.close()
  }

  {
    val reader = CSVReader.open(sample)
    val it = reader.iterator
    println(it.next)
    println(it.next)
    //println(it.next)
    reader.close()
  }

  {
    val reader = CSVReader.open(new File(sample))
    reader.toStream.foreach(println)
    reader.close
  }

  {
    val reader = CSVReader.open(new File(sample))
    reader.foreach(fields => println(fields))
    reader.close
  }

  {
    val reader = CSVReader.open(new File(sample))
    println(reader.readNext())
    println(reader.readNext())
    reader.close()
  }

  {
    val reader = CSVReader.open(new File(`with-headers`))
    println(reader.allWithHeaders())
    reader.close
  }

}
