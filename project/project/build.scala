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
			reflect(sv) % "provided",
			compiler(sv) % "provided", 
			Dependencies.tql
		)) 

	)

	lazy val root = Project(
		id = "build", 
		base = file("rules"), 
		settings = sharedSettings ++ commonDependencies ++ List(
			libraryDependencies ++= Seq(Dependencies.scalahost, Dependencies.scalatest)
		)
	)

}