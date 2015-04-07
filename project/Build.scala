import sbt._
import Keys._

import play._

object Learn extends Build {
  override lazy val settings = super.settings ++ Seq(
    name := "LearningScala",
    version := "Alpha",
    scalaVersion := "2.11.6",
    resolvers := Seq(
      "spray repo" at "http://repo.spray.io",
      "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"
    ),
    scalacOptions in ThisBuild := Seq("-feature", "-deprecation"),
    scalacOptions in Test := Seq("-Yrangepos")
  )

  lazy val commonSettings = Seq(libraryDependencies := Seq("org.specs2" %% "specs2-core" % "3.0.1" % "test"))


  lazy val learningscala = (project in file(".")).aggregate(base, network, testing)
  lazy val base = (project in (subprojects / "base")).settings(commonSettings: _*).settings(
    libraryDependencies ++= Seq(
      "org.scalaz" %% "scalaz-core" % "7.1.1"
    )
  )

  lazy val network = (project in (subprojects / "network")).settings(commonSettings: _*).settings(
    libraryDependencies ++= Seq(
      "com.typesafe.slick" %% "slick" % "2.1.0",
      "io.argonaut" %% "argonaut" % "6.1-M4" // TODO add spray and akka
    )
  ).enablePlugins(PlayScala)

  lazy val testing = (project in (subprojects / "testing")).settings(commonSettings: _*).settings(
    libraryDependencies ++= Seq( // TODO add scalacheck and scalatest
    )
  ) //.dependsOn(shared)

  // lazy val shared = (project in (subprojects / "shared"))

  lazy val subprojects = file("subprojects")
}

