import Dependencies._

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
    resolvers += Resolver.bintrayRepo("giltgroupe", "maven"),
  libraryDependencies ++= Seq(
    compilerPlugin(silencerPlugin),
    silencerLib % Provided,
    csvValidator,
    gfcSemver,

    scalaTest % s"$Test,$IntegrationTest"
  )
)