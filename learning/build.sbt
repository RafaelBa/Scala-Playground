import sbt._
import Keys._

lazy val specsVersion = "4.2.0"
lazy val commonSettings = Seq(
  libraryDependencies := Seq("org.specs2" %% "specs2-core" % specsVersion % "test")
)

lazy val learningscala = (project in file("."))
  .settings(
    name := "LearningScala",
    version := "Alpha",
    scalaVersion := "2.12.6",
    resolvers := Seq(
      "spray repo" at "http://repo.spray.io",
      "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"
    ),
    scalacOptions in ThisBuild := Seq("-feature", "-deprecation"),
    scalacOptions in Test := Seq("-Yrangepos")
  )
  .aggregate(base, network, testing)


lazy val base = (project in (subprojects / "base"))
  .settings(commonSettings: _*)
  .settings(
    libraryDependencies ++= Seq(
      "org.scalaz" %% "scalaz-core" % "7.2.24"
  )
)

/**
  * Note that the appending of the default project settings for Play projects is important
  * because the settings set by enablePlugins are overwritten.
  */
lazy val network = (project in (subprojects / "network"))
  .settings((commonSettings ++ Play.projectSettings): _*)
  .settings(
    testOptions in Test := Seq(Tests.Argument(TestFrameworks.Specs2, "console")),
    libraryDependencies ++= Seq(
      "com.typesafe.slick" %% "slick" % "2.1.0",
      "io.argonaut" %% "argonaut" % "6.2"
    ) // TODO add spray and akka
  ).enablePlugins(PlayScala)

lazy val testing = (project in (subprojects / "testing"))
  .settings(commonSettings: _*)
  .settings(
    libraryDependencies ++= Seq( // TODO add scalatest
      "org.specs2" %% "specs2-scalacheck" % specsVersion % "test"
  )
)
// TODO activate HTML output https://etorreborre.github.io/specs2/guide/SPECS2-3.5/org.specs2.guide.HtmlOutput.html

lazy val subprojects = file("subprojects")

