import Dependencies._
import scala.sys.process._
import java.io.File

val tastyReaderVersion = "2.13.2-SNAPSHOT-withtastyreader"

ThisBuild / scalaVersion     := "2.13.1"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "ch.epfl.scala"

lazy val publishTastyReader = taskKey[Int]("Publish a local version of scala with the tasty-reader")
lazy val publishZio = taskKey[Int]("Publish a local version of zio compatible with the tasty-reader")
lazy val publishZio18 = taskKey[Int]("Publish a local version of zio compatible with the tasty-reader")

publishTastyReader := {
  Process(
    Seq("sbt", """;set baseVersionSuffix in Global := "SNAPSHOT-withtastyreader";publishLocal"""),
    new File("community-projects/tasty-reader")
  ).!
}

publishZio := {
  Process(
    Seq("sbt", """; ++ 0.23.0-RC1!; set coreJVM/version := "1.0.0-RC17-tastycompat"; set stacktracerJVM/version := "1.0.0-RC17-tastycompat"; coreJVM/publishLocal; stacktracerJVM/publishLocal"""),
    new File("community-projects/zio")
  ).!
}

publishZio18 := {
  Process(
    Seq("sbt", """; ++ 0.23.0-RC1!; set coreJVM/version := "1.0.0-RC18-2-tastycompat"; set stacktracerJVM/version := "1.0.0-RC18-2-tastycompat"; coreJVM/publishLocal; stacktracerJVM/publishLocal"""),
    new File("community-projects/zio18")
  ).!
}

lazy val `zio-demo` = (project in file("zio"))
  .settings(
    scalaVersion := tastyReaderVersion,
    name := "tasty-example-project-zio",
    sourceDirectories in Test := Nil,
    libraryDependencies += zio
  )

lazy val `zio18-demo` = (project in file("zio18"))
  .settings(
    scalaVersion := tastyReaderVersion,
    name := "tasty-example-project-zio18",
    sourceDirectories in Test := Nil,
    libraryDependencies += zio18
  )

lazy val `intent-demo` = (project in file("intent"))
  .settings(
    scalaVersion := tastyReaderVersion,
    name := "tasty-example-project-intent",
    sourceDirectories in Compile := Nil,
    libraryDependencies += intent,
    testFrameworks += new TestFramework("intent.sbt.Framework")
  )

lazy val `scalacheck-demo` = (project in file("scalacheck"))
  .settings(
    scalaVersion := tastyReaderVersion,
    name := "tasty-example-project-scalacheck",
    sourceDirectories in Compile := Nil,
    libraryDependencies += scalacheck,
    testOptions in Test += Tests.Argument(TestFrameworks.ScalaCheck, "-maxSize", "5", "-minSuccessfulTests", "33", "-workers", "1", "-verbosity", "1")
  )

lazy val `munit-demo` = (project in file("munit"))
  .settings(
    scalaVersion := tastyReaderVersion,
    name := "tasty-example-project-munit",
    sourceDirectories in Compile := Nil,
    libraryDependencies += munit,
    testFrameworks += new TestFramework("munit.Framework")
  )

lazy val `nanotest-demo` = (project in file("nanotest"))
  .settings(
    scalaVersion := tastyReaderVersion,
    name := "tasty-example-project-nanotest",
    sourceDirectories in Compile := Nil,
    libraryDependencies += nanotest,
    testFrameworks += new TestFramework("verify.runner.Framework")
  )

lazy val root = (project in file("."))
  .aggregate(`zio-demo`)
  .settings(
    sourceDirectories in Compile := Nil,
    sourceDirectories in Test := Nil,
  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
