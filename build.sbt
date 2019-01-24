import Dependencies._
import DependencyOverrides.gfcSemver

lazy val root = (project in file(".")).
  configs(IntegrationTest).             // adds predefined integration test configuration (it)
  settings(
  inThisBuild(List(
    organization := "uk.gov.ons",
    scalaVersion := "2.11.12",
    version      := "0.1.0-SNAPSHOT"
  )),
  name := "br-test-data",
  scalacOptions ++= Seq(
    "-target:jvm-1.8",
    "-encoding", "utf8",
    "-deprecation",
    "-feature",
    "-unchecked",
    "-Xcheckinit",
    "-Xlint:_",
    "-Xfatal-warnings",
    "-Yno-adapted-args",
    "-Ywarn-dead-code",
    "-Ywarn-value-discard",
    "-Ywarn-unused"
  ),
    Defaults.itSettings,
    fork in IntegrationTest := true,
  libraryDependencies ++= Seq(
    compilerPlugin(silencerPlugin),
    silencerLib % Provided,
    csvValidator,
    scalaTest % s"$Test,$IntegrationTest"
  ),
  dependencyOverrides ++= Seq(
    // Favour the  // Favour the jetty components available in Maven Central components available in Maven Central
    gfcSemver
  )
)