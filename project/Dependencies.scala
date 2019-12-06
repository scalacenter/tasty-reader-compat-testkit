import sbt._

object Dependencies {
  lazy val junitInterface = "com.novocode" % "junit-interface"    % "0.11"
  lazy val zio            = "dev.zio"      % "zio_0.20"           % "1.0.0-RC17+77-89a15c00+20191206-1750"//"1.0.0-RC17"
  lazy val dottyLib       = "ch.epfl.lamp" % "dotty-library_0.20" % "0.20.0-RC1"
}
