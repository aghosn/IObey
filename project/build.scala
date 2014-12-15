import sbt._
import Keys._

object build extends Build {
  import Settings._
  
  lazy val commonDependencies = Seq(
    libraryDependencies <++= (scalaVersion)(sv => Seq(
      Dependencies.tql)))

  lazy val core = Project(
    id = "core",
    base = file("core"),
    settings = sharedSettings ++ commonDependencies ++ (obeyplugin.obeyFix += "+{List*}")) dependsOn(rules) enablePlugins(obeyplugin)

  lazy val rules = Project(
    id = "rules",
    base = file("rules"),
    settings = sharedSettings ++ commonDependencies ++ List(
      libraryDependencies ++= Seq(Dependencies.obey_model, Dependencies.scalatest)))

}