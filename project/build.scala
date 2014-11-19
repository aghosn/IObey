import sbt._
import Keys._

object build extends Build {

  lazy val commonSettings = Defaults.defaultSettings ++ Seq(
    scalaVersion := "2.11.2")

  lazy val core = Project(
    id = "core",
    base = file("core"),
    settings = commonSettings)
}