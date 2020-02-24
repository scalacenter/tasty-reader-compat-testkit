import sbt._

object Dependencies {
  lazy val junitInterface = "com.novocode"    % "junit-interface"     % "0.11"
  lazy val zio            = "dev.zio"         % "zio_0.21"            % "1.0.0-RC17+184-5db6514b+20200224-1650"//"1.0.0-RC17"
  lazy val dottyLib       = "ch.epfl.lamp"    % "dotty-library_0.21"  % "0.21.0-RC1"
  lazy val intent         = "com.factor10"    % "intent_0.21"         % "0.4.0"
  lazy val scalacheck     = "org.scalacheck"  % "scalacheck_0.21"     % "1.14.1-SNAPSHOT"
}
