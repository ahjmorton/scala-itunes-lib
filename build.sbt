lazy val root = (project in file (".")).
   settings(
       name := "scala-itunes-lib",
       libraryDependencies += "org.scalatest" % "scalatest_2.10" % "2.0" % "test"
   )
