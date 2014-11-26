package com.ahjmorton.itunesLib

import scala.xml.XML

import org.scalatest._

class ITunesLibAcceptanceTest extends FeatureSpec with GivenWhenThen {

    info("As an ITunesLib user")
    info("I want to be able to get a high level of an iTunes library XML file")
    info("So I can analyse it for other purposes")

    val xml = 
    <plist>
        <dict>
            <key>Tracks</key>
            <dict>
                <key>1</key>
                <dict>
                    <key>Track ID</key><integer>1</integer>
                </dict>
            </dict>
            <key>Playlists</key>
            <array>
                <dict>
                    <key>Name</key><string>Music</string>
                    <key>Playlist Items</key>
                    <array>
                        <dict>
                            <key>Track ID</key><integer>1</integer>
                        </dict>
                    </array>
                </dict>
            </array>
        </dict>
    </plist>

    feature("Loading XML File") {
         scenario("User loads an XML file from the file system") {
             Given("A user has access to iTunes XML")
             val itunesXML = xml
     
             When("A new ITunesLib is created")
             val lib = ITunesLib.create(itunesXML)

             Then("The lib should be a Scala object")
             assert(lib != null)
         }
         scenario("User is able to load music files") {
             Given("A user has access to iTunes XML")
             val itunesXml = xml

             When("A user wants investigate their music")
             val lib = ITunesLib.create(itunesXml)
             val music = lib.music
 
             Then("There should be at least one entry")
             assert(music.size > 0)
             And("It should contain the music from the users library")
         }

    }

}
