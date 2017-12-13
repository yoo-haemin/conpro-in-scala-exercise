
lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.example",
      scalaVersion := "2.12.4",
      version      := "0.1.0-SNAPSHOT",
      fork         := true
    )),
    name := "conpro-in-scala-exercise",
    libraryDependencies += "com.lihaoyi" %% "utest" % "0.6.0" % "test",
    testFrameworks += new TestFramework("utest.runner.Framework")
  )
