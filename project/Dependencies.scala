import sbt._

object Dependencies {
  private lazy val silencerVersion = "1.3.1"

  lazy val scalaMock = "org.scalamock" %% "scalamock" % "4.1.0"
  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.0.4"
  lazy val silencerLib = "com.github.ghik" %% "silencer-lib" % silencerVersion
  lazy val silencerPlugin = "com.github.ghik" %% "silencer-plugin" % silencerVersion
  lazy val slf4jApi = "org.slf4j" % "slf4j-api" % "1.7.25"
  lazy val csvValidator = "uk.gov.nationalarchives" % "csv-validator-core" % "1.1.5"
  lazy val gfcSemver =  "com.gilt" %% "gfc-semver" % "0.1.0"
}
