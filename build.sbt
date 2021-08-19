import Dependencies._
import scala.sys.process._
import java.io.File

val dottyVersion = "3.0.1"
val tastyReaderVersion = "2.13.7-SNAPSHOT-withtastyreader"

ThisBuild / scalaVersion := "2.13.6"
ThisBuild / version      := "0.1.0-SNAPSHOT"
ThisBuild / organization := "ch.epfl.scala"

lazy val publishTastyReader = taskKey[Int]("Publish a local version of scala with the tasty-reader")
lazy val publishZio = taskKey[Int]("Publish a local version of zio compatible with the tasty-reader")
lazy val publishZio18 = taskKey[Int]("Publish a local version of zio compatible with the tasty-reader")
lazy val publishScalacheck = taskKey[Int]("Publish a local version of scalacheck compatible with the tasty-reader")

def commandInDir(command: String, dir: String) = Process(Seq("sbt", command), new File(dir)).!

lazy val commonSettings = Seq(
  scalacOptions += "-Ytasty-reader"
)

publishTastyReader := commandInDir(
  command = """; set Global / baseVersionSuffix := "SNAPSHOT-withtastyreader"; clean; publishLocal""",
  dir     = "community-projects/tasty-reader"
)

publishZio := commandInDir(
  command = s"""; ++ $dottyVersion!; set coreJVM/version := "1.0.0-RC17-tastycompat"; set stacktracerJVM/version := "1.0.0-RC17-tastycompat"; coreJVM/publishLocal; stacktracerJVM/publishLocal""",
  dir     = "community-projects/zio"
)

publishZio18 := commandInDir(
  command = s"""; ++ $dottyVersion!; set coreJVM/version := "1.0.0-RC18-2-tastycompat"; set stacktracerJVM/version := "1.0.0-RC18-2-tastycompat"; coreJVM/publishLocal; stacktracerJVM/publishLocal""",
  dir     = "community-projects/zio18"
)

publishScalacheck := commandInDir(
  command = s"""; ++ $dottyVersion!; jvm/publishLocal""",
  dir     = "community-projects/scalacheck"
)

lazy val `zio-demo` = (project in file("zio"))
  .settings(commonSettings)
  .settings(
    scalaVersion := tastyReaderVersion,
    name := "tasty-example-project-zio",
    libraryDependencies += zio
  )

lazy val `zio18-demo` = (project in file("zio18"))
  .settings(commonSettings)
  .settings(
    scalaVersion := tastyReaderVersion,
    name := "tasty-example-project-zio18",
    libraryDependencies += zio18
  )

lazy val `intent-demo` = (project in file("intent"))
  .settings(commonSettings)
  .settings(
    scalaVersion := tastyReaderVersion,
    name := "tasty-example-project-intent",
    libraryDependencies += intent,
    testFrameworks += new TestFramework("intent.sbt.Framework")
  )

lazy val `scalacheck-demo` = (project in file("scalacheck"))
  .settings(commonSettings)
  .settings(
    scalaVersion := tastyReaderVersion,
    name := "tasty-example-project-scalacheck",
    libraryDependencies += scalacheck,
    Test / testOptions += Tests.Argument(TestFrameworks.ScalaCheck, "-maxSize", "5", "-minSuccessfulTests", "33", "-workers", "1", "-verbosity", "1")
  )

lazy val `munit-demo` = (project in file("munit"))
  .settings(commonSettings)
  .settings(
    scalaVersion := tastyReaderVersion,
    name := "tasty-example-project-munit",
    libraryDependencies ++= Seq(munit, scalaReflect),
    testFrameworks += new TestFramework("munit.Framework")
  )

lazy val `nanotest-demo` = (project in file("nanotest"))
  .settings(commonSettings)
  .settings(
    scalaVersion := tastyReaderVersion,
    name := "tasty-example-project-nanotest",
    libraryDependencies += nanotest,
    testFrameworks += new TestFramework("verify.runner.Framework")
  )

lazy val `circe-demo` = (project in file("circe"))
  .settings(commonSettings)
  .settings(
    scalaVersion := tastyReaderVersion,
    name := "tasty-example-project-circe",
    libraryDependencies ++= Seq(circe, `circe-parser`, `circe-generic`)
  )


lazy val root = (project in file("."))
  .aggregate(`zio-demo`)

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
