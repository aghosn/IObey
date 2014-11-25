import sbt._
import Keys._
import sbtassembly.Plugin._
import AssemblyKeys._
import complete.DefaultParsers._

object build extends Build {
  import Dependencies._
  import Settings._
  
  lazy val commonDependencies = Seq(
    libraryDependencies <++= (scalaVersion)(sv => Seq(
      Dependencies.tql)))

  lazy val core = Project(
    id = "core",
    base = file("core"),
    settings = sharedSettings ++ commonDependencies ++ Seq(
    scalacOptions ++= Seq(
      "-Xplugin:"+Dependencies.obey_plugin,
      "-P:obey:addRules:rules/target/scala-2.11/classes"))) dependsOn(rules)

  lazy val rules = Project(
    id = "rules",
    base = file("rules"),
    settings = sharedSettings ++ commonDependencies ++ List(
      libraryDependencies ++= Seq(Dependencies.scalatest)))

}