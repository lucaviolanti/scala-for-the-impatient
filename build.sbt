version := "0.1"

scalaVersion := "2.12.7"

lazy val root = (project in file(".")).
  settings(
    projectSettings,
    compilerSettings
  )

lazy val projectSettings = Seq(
  name := "scala-for-the-impatient"
)

lazy val compilerSettings = Seq(
  scalacOptions ++= compilerOptions
)

lazy val compilerOptions = Seq(
  "-encoding", "utf8",
  "-language:implicitConversions",
  "-language:higherKinds",
  "-language:postfixOps"
)
