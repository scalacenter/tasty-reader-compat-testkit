import Dependencies._
import scala.sys.process._
import java.io.File

val tastyReaderVersion = "2.13.2-SNAPSHOT-withtastyreader"

ThisBuild / scalaVersion     := "2.13.1"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "ch.epfl.scala"

lazy val publishTastyReader = taskKey[Int]("Publish a local version of scala with the tasty-reader")

publishTastyReader := {
  Process(
    Seq("sbt", """;set baseVersionSuffix in Global := "SNAPSHOT-withTASTyReader";publishLocal"""),
    new File("community-projects/tasty-reader")
  ).!
}

lazy val `zio-demo` = (project in file("zio"))
  .settings(
    scalaVersion := tastyReaderVersion,
    name := "tasty-example-project-zio",
    sourceDirectories in Test := Nil,
    libraryDependencies += dottyLib,
    libraryDependencies += zio
  )

lazy val `intent-demo` = (project in file("intent"))
  .settings(
    scalaVersion := tastyReaderVersion,
    name := "tasty-example-project-intent",
    sourceDirectories in Compile := Nil,
    libraryDependencies += dottyLib,
    libraryDependencies += intent,
    testFrameworks += new TestFramework("intent.sbt.Framework")
  )

lazy val `scalacheck-demo` = (project in file("scalacheck"))
  .settings(
    scalaVersion := tastyReaderVersion,
    name := "tasty-example-project-scalacheck",
    sourceDirectories in Compile := Nil,
    libraryDependencies += dottyLib,
    libraryDependencies += scalacheck,
    testOptions in Test += Tests.Argument(TestFrameworks.ScalaCheck, "-maxSize", "5", "-minSuccessfulTests", "33", "-workers", "1", "-verbosity", "1")
  )

lazy val root = (project in file("."))
  .aggregate(`zio-demo`)
  .settings(
    sourceDirectories in Compile := Nil,
    sourceDirectories in Test := Nil,
  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
