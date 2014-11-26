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
                <key>2</key>
                <dict>
                    <key>Track ID</key><integer>2</integer>
                </dict>
                <key>3</key>
                <dict>
                    <key>Track ID</key><integer>3</integer>
                </dict>
                <key>4</key>
                <dict>
                    <key>Track ID</key><integer>4</integer>
                </dict>
                <key>5</key>
                <dict>
                    <key>Track ID</key><integer>5</integer>
                </dict>
                <key>6</key>
                <dict>
                    <key>Track ID</key><integer>6</integer>
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
                <dict>
                    <key>Name</key><string>Audiobooks</string>
                    <key>Playlist Items</key>
                    <array>
                        <dict>
                            <key>Track ID</key><integer>2</integer>
                        </dict>
                    </array>
                </dict>
                <dict>
                    <key>Name</key><string>Movies</string>
                    <key>Playlist Items</key>
                    <array>
                        <dict>
                            <key>Track ID</key><integer>3</integer>
                        </dict>
                    </array>
                </dict>
                <dict>
                    <key>Name</key><string>Podcasts</string>
                    <key>Playlist Items</key>
                    <array>
                        <dict>
                            <key>Track ID</key><integer>4</integer>
                        </dict>
                    </array>
                </dict>
                <dict>
                    <key>Name</key><string>TV Shows</string>
                    <key>Playlist Items</key>
                    <array>
                        <dict>
                            <key>Track ID</key><integer>5</integer>
                        </dict>
                    </array>
                </dict>
                <dict>
                    <key>Name</key><string>iTunes U</string>
                    <key>Playlist Items</key>
                    <array>
                        <dict>
                            <key>Track ID</key><integer>6</integer>
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
             assert(music.size == 1)
         }
         scenario("User is able to load audiobook files") {
             Given("A user has access to iTunes XML")
             val itunesXml = xml

             When("A user wants investigate their audiobooks")
             val lib = ITunesLib.create(itunesXml)
             val audioBooks = lib.audioBooks
 
             Then("There should be at least one entry")
             assert(audioBooks.size == 1)
         }
         scenario("User is able to load movie files") {
             Given("A user has access to iTunes XML")
             val itunesXml = xml

             When("A user wants investigate their movies")
             val lib = ITunesLib.create(itunesXml)
             val movies = lib.movies
 
             Then("There should be at least one entry")
             assert(movies.size == 1)
         }
         scenario("User is able to load podcast files") {
             Given("A user has access to iTunes XML")
             val itunesXml = xml

             When("A user wants investigate their podcasts")
             val lib = ITunesLib.create(itunesXml)
             val podcasts = lib.podcasts
 
             Then("There should be at least one entry")
             assert(podcasts.size == 1)
         }
         scenario("User is able to load tvShow files") {
             Given("A user has access to iTunes XML")
             val itunesXml = xml

             When("A user wants investigate their tvShows")
             val lib = ITunesLib.create(itunesXml)
             val tvShows = lib.tvShows
 
             Then("There should be at least one entry")
             assert(tvShows.size == 1)
         }
         scenario("User is able to load iTunesU files") {
             Given("A user has access to iTunes XML")
             val itunesXml = xml

             When("A user wants investigate their iTunesUs")
             val lib = ITunesLib.create(itunesXml)
             val iTunesUs = lib.itunesU
 
             Then("There should be at least one entry")
             assert(iTunesUs.size == 1)
         }
    }
}
