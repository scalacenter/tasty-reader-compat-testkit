import Dependencies._
import scala.sys.process._
import java.io.File

lazy val tastyReaderVersion = "2.13.2-SNAPSHOT-withTASTyReader"

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

lazy val example = (project in file("example"))
  .settings(
    scalaVersion := tastyReaderVersion,
    name := "tasty-example-project",
    libraryDependencies += junitInterface % Test,
    libraryDependencies += dottyLib,
    libraryDependencies += zio
  )

lazy val root = (project in file("."))
  .aggregate(example)
  .settings(
    sourceDirectories in Compile := Nil,
    sourceDirectories in Test := Nil,
  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
