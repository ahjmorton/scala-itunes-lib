lazy val root = (project in file (".")).
   settings(
       name := "itunesLib",
       scalaVersion := "2.12.10",
       organization := "com.ahjmorton",
       version := "0.0.4-SNAPSHOT",
       libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "1.2.0",
       libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.0" % "test"
   )
