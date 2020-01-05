import sbt._

object Dependencies {
  lazy val junitInterface = "com.novocode"    % "junit-interface"     % "0.11"
  lazy val zio            = "dev.zio"         % "zio_0.20"            % "1.0.0-RC17+184-5db6514b"//"1.0.0-RC17"
  lazy val dottyLib       = "ch.epfl.lamp"    % "dotty-library_0.20"  % "0.20.0-RC1"
  lazy val intent         = "com.factor10"    % "intent_0.20"         % "0.3.0"
  lazy val scalacheck     = "org.scalacheck"  % "scalacheck_0.20"     % "1.14.1-SNAPSHOT"
}
