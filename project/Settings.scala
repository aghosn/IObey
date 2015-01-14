import sbt._
import Keys._

object Settings {
	lazy val languageVersion = "2.11.5"
	lazy val metaVersion = "0.1.0-SNAPSHOT"

	lazy val sharedSettings: Seq[sbt.Def.Setting[_]] = Defaults.defaultSettings ++ Seq(
		scalaVersion := languageVersion, 
		crossVersion := CrossVersion.full, 
		version := metaVersion, 
		organization := "org.scalameta",
		description := "Code Health compiler plugin for scalameta trees",
		description := "Test Scala host for scala.meta",
    resolvers += Resolver.sonatypeRepo("snapshots"),
    resolvers += Resolver.sonatypeRepo("releases"),
    scalacOptions ++= Seq("-feature", "-deprecation", "-unchecked"),
    parallelExecution in Test := false, // hello, reflection sync!!
    logBuffered := false
		/*scalaHome := {
      val scalaHome = System.getProperty("scalahost.scala.home") //TODO Change this maybe 
      if (scalaHome != null) {
        println(s"Going for custom scala home at $scalaHome")
        Some(file(scalaHome))
      } else None
    }*/
	)

  lazy val dontPackage = packagedArtifacts := Map.empty
	
	// Thanks Jason for this cool idea (taken from https://github.com/retronym/boxer)
  // add plugin timestamp to compiler options to trigger recompile of
  // main after editing the plugin. (Otherwise a 'clean' is needed.)
  def usePlugin(plugin: ProjectReference) =
    scalacOptions <++= (Keys.`package` in (plugin, Compile)) map { (jar: File) =>
      System.setProperty("sbt.paths.plugin.jar", jar.getAbsolutePath)
      Seq("-Xplugin:" + jar.getAbsolutePath, "-Jdummy=" + jar.lastModified)
    }

  def exposeClasspaths(projectName: String) = Seq(
    fullClasspath in Test := {
      val defaultValue = (fullClasspath in Test).value
      val classpath = defaultValue.files.map(_.getAbsolutePath)
      System.setProperty("sbt.paths.tests.classpath", classpath.mkString(java.io.File.pathSeparatorChar.toString))
      defaultValue
    },
    resourceDirectory in Test := {
      val defaultValue = (resourceDirectory in Test).value
      System.setProperty("sbt.paths.tests.resources", defaultValue.getAbsolutePath)
      defaultValue
    }
  )
}