import sbt._
import Keys._
import sbtassembly.Plugin._
import AssemblyKeys._
import complete.DefaultParsers._
import obeyplugin._

object build extends Build {
  import Dependencies._
  import Settings._
  
  lazy val commonDependencies = Seq(
    libraryDependencies <++= (scalaVersion)(sv => Seq(
      Dependencies.tql)))

  lazy val core = Project(
    id = "core",
    base = file("core"),
    settings = sharedSettings ++ commonDependencies ++ obeySettings ++ Seq(obeyFormatPos ++= Seq("List", "Set"))) dependsOn(rules)

  lazy val rules = Project(
    id = "rules",
    base = file("rules"),
    settings = sharedSettings ++ commonDependencies ++ List(
      libraryDependencies ++= Seq(Dependencies.obey_model, Dependencies.scalatest)))

}