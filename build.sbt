lazy val root = (project in file (".")).
   settings(
       name := "scala-itunes-lib",
       libraryDependencies += "com.github.nscala-time" %% "nscala-time" % "1.6.0",
       libraryDependencies += "org.scalatest" % "scalatest_2.10" % "2.0" % "test"
   )
