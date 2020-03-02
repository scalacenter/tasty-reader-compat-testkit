import sbt._

object Dependencies {
  lazy val junitInterface = "com.novocode"   % "junit-interface" % "0.11"
  lazy val zio            = "dev.zio"        % "zio_0.22"        % "1.0.0-RC17-tastycompat"
  lazy val intent         = "com.factor10"   % "intent_0.22"     % "0.5.0"
  lazy val scalacheck     = "org.scalacheck" % "scalacheck_0.22" % "1.14.1-SNAPSHOT"
}
