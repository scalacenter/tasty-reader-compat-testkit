val Scala213 = "2.13.5"

val platformSuffix = settingKey[String]("platform binary suffix")

lazy val shared = Seq(
  scalaVersion := Scala213,
  scalacOptions += "-Ytasty-reader",
  Test / scalacOptions += "-Ytasty-reader"
)

lazy val crossShared = Def.settings(
  platformSuffix := (if (crossProjectPlatform.value == JSPlatform) "_sjs1" else ""),
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
    name := "test-tastyreader-circe-generic",
    libraryDependencies += "io.circe" % "circe-core_3.0.0-RC1" % "0.14.0-M4",
    libraryDependencies += "io.circe" % "circe-generic_3.0.0-RC1" % "0.14.0-M4"
  )
  .settings(shared)

lazy val `circe-parse-demo` = (project in file("circe-parse"))
  .settings(
    name := "test-tastyreader-circe-parse",
    libraryDependencies += "io.circe" % "circe-core_3.0.0-RC1" % "0.14.0-M4",
    libraryDependencies += "io.circe" % "circe-parser_3.0.0-RC1" % "0.14.0-M4"
  )
  .settings(shared)

lazy val `cats-effect-demo` = crossProject(JSPlatform, JVMPlatform)
  .withoutSuffixFor(JVMPlatform)
  .crossType(CrossType.Pure)
  .in(file("cats-effect"))
  .settings(
    name := "test-tastyreader-cats-effect",
    libraryDependencies += "org.typelevel" % s"cats-effect${platformSuffix.value}_3.0.0-RC1" % "3.0.0-RC2"
  )
  .jsSettings(
    scalaJSUseMainModuleInitializer := true,
  )
  .settings(shared, crossShared)

lazy val `fs2-demo` = (project in file("fs2"))
  .settings(
    name := "test-tastyreader-fs2",
    libraryDependencies += "co.fs2" % "fs2-core_3.0.0-RC1" % "3.0-43-5b2b437", // For cats 2 and cats-effect 2
    libraryDependencies += "co.fs2" % "fs2-io_3.0.0-RC1" % "3.0-43-5b2b437",
    libraryDependencies += "co.fs2" % "fs2-reactive-streams_3.0.0-RC1" % "3.0-43-5b2b437",
    libraryDependencies += "co.fs2" % "fs2-experimental_3.0.0-RC1" % "2.5.3",
    libraryDependencies += "org.typelevel" % "cats-effect_3.0.0-RC1" % "3.0.0-RC2"
  )
  .settings(shared)

lazy val `skunk-demo` = (project in file("skunk"))
  .settings(
    name := "test-tastyreader-skunk",
    libraryDependencies +=   "org.tpolecat" % "skunk-core_3.0.0-RC1" % "0.0.24",
  )
  .settings(shared)

lazy val `sttp-demo` = (project in file("sttp"))
  .settings(
    name := "test-tastyreader-sttp",
    libraryDependencies += "com.softwaremill.sttp.client3" % "core_3.0.0-RC1" % "3.1.6"
  )
  .settings(shared)

lazy val `scodec-demo` = (project in file("scodec"))
  .settings(
    name := "test-tastyreader-scodec",
    libraryDependencies += "org.scodec" % "scodec-core_3.0.0-RC1" % "2.0.0-RC1"
  )
  .settings(shared)

lazy val `sourcecode-demo` = (project in file("sourcecode"))
  .settings(
    name := "test-tastyreader-sourcecode",
    libraryDependencies += "com.lihaoyi" % "sourcecode_3.0.0-RC1" % "0.2.3",
    libraryDependencies += "org.scala-lang" % "scala3-library_3.0.0-RC1" % "3.0.0-RC1",
  )
  .settings(shared)


lazy val `natchez-demo` = (project in file("natchez"))
  .settings(
    name := "test-tastyreader-natchez",
    libraryDependencies += "org.tpolecat" % "natchez-core_3.0.0-RC1" % "0.0.20",
    libraryDependencies += "org.tpolecat" % "natchez-jaeger_3.0.0-RC1" % "0.0.20",
    libraryDependencies += "org.typelevel" % "cats-effect_3.0.0-RC1" % "2.3.3",
    addCompilerPlugin("org.typelevel" %% "kind-projector" % "0.11.3" cross CrossVersion.full)
  )
  .settings(shared)

lazy val `log4s-demo` = (project in file("log4s"))
  .settings(
    name := "test-tastyreader-log4s",
    libraryDependencies += "org.log4s" % "log4s_3.0.0-RC1" % "1.10.0-M5"
  )
  .settings(shared)

lazy val `utest-demo` = (project in file("utest"))
  .settings(
    name := "test-tastyreader-utest",
    libraryDependencies += "com.lihaoyi" % "utest_3.0.0-RC1" % "0.7.7" % "test",
    libraryDependencies += "org.scala-lang" % "scala3-library_3.0.0-RC1" % "3.0.0-RC1",
    testFrameworks += new TestFramework("utest.runner.Framework")
  )
  .settings(shared)


lazy val `geny-demo` = (project in file("geny"))
  .settings(
    name := "geny",
    libraryDependencies += "com.lihaoyi" % "geny_3.0.0-RC1" % "0.6.5",
    libraryDependencies += "org.scala-lang" % "scala3-library_3.0.0-RC1" % "3.0.0-RC1"
  )
  .settings(shared)

lazy val `scala-csv-demo` = (project in file("scala-csv"))
  .settings(
    name := "test-tastyreader-scala-csv",
    libraryDependencies += "com.github.tototoshi" % "scala-csv_3.0.0-RC1" % "1.3.7"
  )
  .settings(shared)

lazy val `monocle-demo` = (project in file("monocle"))
  .settings(
    name := "test-monocle",
    libraryDependencies += "com.github.julien-truffaut" % "monocle-core_3.0.0-RC1"  % "3.0.0-M3"
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
