import sbtrelease.ReleasePlugin.ReleaseKeys._

organization := "com.ivanstajic"

name := "akka-htt-swagger"

scalaVersion := "2.11.7"

crossScalaVersions := Seq("2.10.5", "2.11.7")

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.2.0" % "test" ,
  "com.wordnik" %% "swagger-core" % "1.3.12" excludeAll( ExclusionRule(organization = "org.json4s"),  ExclusionRule(organization="org.fasterxml*") ),
  "com.typesafe.akka" %% "akka-actor" % "2.3.12",
  "com.typesafe.akka" % "akka-http-core-experimental_2.11" % "1.0",
  "com.typesafe.akka" % "akka-http-experimental_2.11" % "1.0",
  "org.json4s" %% "json4s-jackson" % "3.2.11",
  "org.json4s" %% "json4s-native" % "3.2.11",
  "joda-time" % "joda-time" % "2.2",
  "org.joda" % "joda-convert" % "1.3.1",
  "com.typesafe.scala-logging" %% "scala-logging-slf4j" % "2.1.2",
  "javax.ws.rs" % "jsr311-api" % "1.1.1"
)

releaseSettings

crossBuild := true

testOptions in Test += Tests.Argument("-oD")

parallelExecution in Test := false

publishMavenStyle := true

publishTo <<= version { (v: String) =>
  val nexus = "https://oss.sonatype.org/"
  if (v.trim.endsWith("SNAPSHOT"))
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

parallelExecution in Test := false

homepage := Some(url("https://github.com/ivanstajic/akka-http-swagger"))

licenses := Seq("The Apache Software License, Version 2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0.txt"))

net.virtualvoid.sbt.graph.Plugin.graphSettings

publishArtifactsAction := PgpKeys.publishSigned.value

pomExtra := (
  <scm>
    <url>git@github.com:ivanstajic/akka-http-swagger.git</url>
    <connection>scm:git:git@github.com:ivanstajic/akka-http-swagger.git</connection>
  </scm>
  <developers>
  </developers>)
