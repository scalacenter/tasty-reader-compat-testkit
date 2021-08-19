val Scala213 = "2.13.6"
val Scala3   = "3.0.1"

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
    name := "scalatest-tastyreader-demo",
    libraryDependencies += ("org.scalatest" %% "scalatest" % "3.2.9").cross(CrossVersion.for2_13Use3),
  )
  .settings(shared)

lazy val `munit-demo` = (project in file("munit"))
  .settings(
    name := "munit-tastyreader-demo",
    libraryDependencies += ("org.scalameta" %% "munit" % "0.7.28").cross(CrossVersion.for2_13Use3),
    libraryDependencies += "org.scala-lang" % "scala-reflect" % Scala213,
  )
  .settings(shared)

lazy val `scalacheck-demo` = (project in file("scalacheck"))
  .settings(
    name := "scalacheck-tastyreader-demo",
    libraryDependencies += ("org.scalacheck" %% "scalacheck" % "1.15.4").cross(CrossVersion.for2_13Use3),
    Test / testOptions += Tests.Argument(TestFrameworks.ScalaCheck, "-maxSize", "5", "-minSuccessfulTests", "33", "-workers", "1", "-verbosity", "1")
  )
  .settings(shared)

lazy val `nanotest-demo` = (project in file("nanotest"))
  .settings(
    name := "nanotest-tastyreader-demo",
    libraryDependencies += ("com.eed3si9n.verify" %% "verify" % "1.0.0").cross(CrossVersion.for2_13Use3)
  )
  .settings(shared)

lazy val `circe-generic-demo` = (project in file("circe-generic"))
  .settings(
    name := "circe-generic-tastyreader-demo",
    libraryDependencies ++= Seq(
      "io.circe" %% "circe-core"   % "0.14.1",
      "io.circe" %% "circe-parser" % "0.14.1"
    ).map(_.cross(CrossVersion.for2_13Use3)),
  )
  .settings(shared)

lazy val `circe-parse-demo` = (project in file("circe-parse"))
  .settings(
    name := "circe-parse-tastyreader-demo",
    libraryDependencies ++= Seq(
      "io.circe" %% "circe-core"   % "0.14.1",
      "io.circe" %% "circe-parser" % "0.14.1"
    ).map(_.cross(CrossVersion.for2_13Use3)),
  )
  .settings(shared)

lazy val `cats-effect-demo` = crossProject(JSPlatform, JVMPlatform)
  .withoutSuffixFor(JVMPlatform)
  .crossType(CrossType.Pure)
  .in(file("cats-effect"))
  .settings(
    name := "cats-effect-tastyreader-demo",
    libraryDependencies += ("org.typelevel" %% s"cats-effect${platformSuffix.value}" % "3.2.3").cross(CrossVersion.for2_13Use3)
  )
  .jsSettings(
    scalaJSUseMainModuleInitializer := true,
  )
  .settings(shared, crossShared)

lazy val `fs2-demo` = (project in file("fs2"))
  .settings(
    name := "fs2-tastyreader-demo",
    libraryDependencies ++= Seq(
      "co.fs2"        %% "fs2-core"             % "3.1.0",
      "co.fs2"        %% "fs2-io"               % "3.1.0",
      "co.fs2"        %% "fs2-reactive-streams" % "3.1.0",
      "org.typelevel" %% "cats-effect"          % "3.2.3"
    ).map(_.cross(CrossVersion.for2_13Use3))
  )
  .settings(shared)

lazy val `skunk-demo` = (project in file("skunk"))
  .settings(
    name := "skunk-tastyreader-demo",
    libraryDependencies += ("org.tpolecat" %% "skunk-core" % "0.2.2").cross(CrossVersion.for2_13Use3),
  )
  .settings(shared)

lazy val `sttp-demo` = (project in file("sttp"))
  .settings(
    name := "sttp-tastyreader-demo",
    libraryDependencies += ("com.softwaremill.sttp.client3" %% "core" % "3.3.13").cross(CrossVersion.for2_13Use3)
  )
  .settings(shared)

lazy val `scodec-demo` = (project in file("scodec"))
  .settings(
    name := "scodec-tastyreader-demo",
    libraryDependencies ++= Seq(
      "org.scodec" %% "scodec-core" % "2.0.0",
      "org.scodec" %% "scodec-bits" % "1.1.27"
    ).map(_.cross(CrossVersion.for2_13Use3))
  )
  .settings(shared)

lazy val `sourcecode-demo` = (project in file("sourcecode"))
  .settings(
    name := "sourcecode-tastyreader-demo",
    libraryDependencies ++= Seq(
      "com.lihaoyi"    %% "sourcecode"     % "0.2.7",
      "org.scala-lang" %% "scala3-library" % Scala3
    ).map(_.cross(CrossVersion.for2_13Use3)),
  )
  .settings(shared)


lazy val `natchez-demo` = (project in file("natchez"))
  .settings(
    name := "natchez-tastyreader-demo",
    libraryDependencies ++= Seq(
      "org.tpolecat"  %% "natchez-core"   % "0.1.5",
      "org.tpolecat"  %% "natchez-jaeger" % "0.1.5",
      "org.typelevel" %% "cats-effect"    % "3.2.3"
    ).map(_.cross(CrossVersion.for2_13Use3)),
    addCompilerPlugin("org.typelevel" % "kind-projector" % "0.13.0" cross CrossVersion.full)
  )
  .settings(shared)

lazy val `log4s-demo` = (project in file("log4s"))
  .settings(
    name := "log4s-tastyreader-demo",
    libraryDependencies += ("org.log4s" %% "log4s" % "1.10.0").cross(CrossVersion.for2_13Use3)
  )
  .settings(shared)

lazy val `utest-demo` = (project in file("utest"))
  .settings(
    name := "utest-tastyreader-demo",
    libraryDependencies ++= Seq(
      "com.lihaoyi"    %% "utest"          % "0.7.10" % "test",
      "org.scala-lang" %% "scala3-library" % Scala3,
    ).map(_.cross(CrossVersion.for2_13Use3)),
    testFrameworks += new TestFramework("utest.runner.Framework")
  )
  .settings(shared)


lazy val `geny-demo` = (project in file("geny"))
  .settings(
    name := "geny-tastyreader-demo",
    libraryDependencies ++= Seq(
      "com.lihaoyi"    %% "geny"           % "0.6.10",
      "org.scala-lang" %% "scala3-library" % Scala3,
    ).map(_.cross(CrossVersion.for2_13Use3)),
  )
  .settings(shared)

lazy val `scala-csv-demo` = (project in file("scala-csv"))
  .settings(
    name := "scala-csv-tastyreader-demo",
    libraryDependencies += ("com.github.tototoshi" %% "scala-csv" % "1.3.8").cross(CrossVersion.for2_13Use3)
  )
  .settings(shared)

lazy val `monocle-demo` = (project in file("monocle"))
  .settings(
    name := "monocle-tastyreader-demo",
    libraryDependencies += ("dev.optics" %% "monocle-core" % "3.0.0").cross(CrossVersion.for2_13Use3)
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
