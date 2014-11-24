package com.ahjmorton.itunesLib

import scala.xml.XML

import org.scalatest._

class ITunesLibAcceptanceTest extends FeatureSpec with GivenWhenThen {

    info("As an ITunesLib user")
    info("I want to be able to get a high level of an iTunes library XML file")
    info("So I can analyse it for other purposes")

    def loadXml = XML.loadFile("/Users/ahjmorton/Music/iTunes/iTunes Music Library.xml") 

    feature("Loading XML File") {
         scenario("User loads an XML file from the file system") {
             Given("A user has access to iTunes XML")
             val itunesXML = loadXml
     
             When("A new ITunesLib is created")
             val lib = ITunesLib.create(itunesXML)

             Then("The lib should be a Scala object")
             assert(lib != null)
         }
    }

}
