val Scala213 = "2.13.5"

lazy val shared = Seq(
  scalaVersion := Scala213,
  scalacOptions += "-Ytasty-reader",
  Test / scalacOptions += "-Ytasty-reader"
)

lazy val `scalatest-demo` = (project in file("scalatest"))
  .settings(
    name := "test-tastyreader-scalacheck",
    libraryDependencies += "org.scalatest" % "scalatest_3.0.0-RC1" % "3.2.5",
  )
  .settings(shared)

lazy val `munit-demo` = (project in file("munit"))
  .settings(
    name := "test-tastyreader-munit",
    libraryDependencies += "org.scalameta" % "munit_3.0.0-RC1" % "0.7.22",
    libraryDependencies += "org.scala-lang" % "scala-reflect" % Scala213,
  )
  .settings(shared)

lazy val `scalacheck-demo` = (project in file("scalacheck"))
  .settings(
    name := "test-tastyreader-scalacheck",
    libraryDependencies += "org.scalacheck" % "scalacheck_3.0.0-RC1" % "1.15.3",
    testOptions in Test += Tests.Argument(TestFrameworks.ScalaCheck, "-maxSize", "5", "-minSuccessfulTests", "33", "-workers", "1", "-verbosity", "1")
  )
  .settings(shared)

lazy val `nanotest-demo` = (project in file("nanotest"))
  .settings(
    name := "test-tastyreader-nanotest",
    libraryDependencies += "com.eed3si9n.verify" % "verify_3.0.0-RC1" % "1.0.0"
  )
  .settings(shared)

lazy val `circe-generic-demo` = (project in file("circe-generic"))
  .settings(
    name := "test-tastyreader-circe",
    libraryDependencies += "io.circe" % "circe-core_3.0.0-RC1" % "0.14.0-M4",
    libraryDependencies += "io.circe" % "circe-generic_3.0.0-RC1" % "0.14.0-M4"
  )
  .settings(shared)

lazy val `circe-parse-demo` = (project in file("circe-parse"))
  .settings(
    name := "test-tastyreader-circe",
    libraryDependencies += "io.circe" % "circe-core_3.0.0-RC1" % "0.14.0-M4",
    libraryDependencies += "io.circe" % "circe-parser_3.0.0-RC1" % "0.14.0-M4"
  )
  .settings(shared)

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "ch.epfl.scala",
      scalaVersion := Scala213
    )),
    name := "test-tastyreader",
  )
