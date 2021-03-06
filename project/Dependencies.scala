import sbt._

object Dependencies {
	import Settings.metaVersion 
	
	def reflect(sv: String) = "org.scala-lang" % "scala-reflect" % sv
  def compiler(sv: String) = "org.scala-lang" % "scala-compiler" % sv
  
  lazy val tql = "com.github.begeric" % "tqlscalameta_2.11" % "0.1-SNAPSHOT"
  
  lazy val scalatest = "org.scalatest" %% "scalatest" % "2.1.3" % "test"
  lazy val scalacheck = "org.scalacheck" %% "scalacheck" % "1.11.3" % "test"

  lazy val obey_model = "com.github.aghosn" % "model_2.11.5" % "0.1.0-SNAPSHOT"
  lazy val obey_plugin = "com.github.aghosn" % "plugin_2.11.5" % "0.1.0-SNAPSHOT"

}