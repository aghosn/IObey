resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

dependencyOverrides += "org.scala-sbt" % "sbt" % "0.13.7"

addSbtPlugin("com.github.aghosn" %% "sbt-obeyplugin" % "0.1-SNAPSHOT")