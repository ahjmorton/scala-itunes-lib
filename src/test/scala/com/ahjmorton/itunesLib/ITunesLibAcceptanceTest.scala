package com.ahjmorton.itunesLib

import scala.xml.XML

import org.scalatest._

class ITunesLibAcceptanceTest extends FeatureSpec with GivenWhenThen {

    info("As an ITunesLib user")
    info("I want to be able to get a high level of an iTunes library XML file")
    info("So I can analyse it for other purposes")

    val loadXml = XML.loadFile("/Users/ahjmorton/Music/iTunes/iTunes Music Library.xml") 

    val xml = 
    <plist>
        <dict>
        </dict>
    </plist>

    feature("Loading XML File") {
         scenario("User loads an XML file from the file system") {
             Given("A user has access to iTunes XML")
             val itunesXML = loadXml
     
             When("A new ITunesLib is created")
             val lib = ITunesLib.create(itunesXML)

             Then("The lib should be a Scala object")
             assert(lib != null)
         }
         scenario("User is able to load music files") {
             Given("A user has access to iTunes XML")
             val itunesXml = loadXml

             When("A user wants investigate their music")
             val lib = ITunesLib.create(itunesXml)
             val music = lib.music
 
             Then("There should be at least one entry")
             assert(music.size > 0)
             And("It should contain the music from the users library")
         }
    }

}
