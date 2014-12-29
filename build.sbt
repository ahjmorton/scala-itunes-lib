lazy val root = (project in file (".")).
   settings(
       name := "itunesLib",
       organization := "com.ahjmorton",
       version := "0.0.3-SNAPSHOT",
       libraryDependencies += "com.github.nscala-time" %% "nscala-time" % "1.6.0",
       libraryDependencies += "org.scalatest" % "scalatest_2.10" % "2.0" % "test"
   )
