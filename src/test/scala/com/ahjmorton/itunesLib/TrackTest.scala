package com.ahjmorton.itunesLib

import org.joda.time.DateTime

import org.scalatest._

class MusicFileTest extends FlatSpec with Matchers {

      val xml = 
          <dict>
              <key>Track ID</key><integer>1</integer>
              <key>Name</key><string>Never Give Up</string>
              <key>Artist</key><string>Error404</string>
              <key>Album</key><string>Beta EP</string>
              <key>Kind</key><string>Apple Lossless audio file</string>
              <key>Size</key><integer>31350039</integer>
              <key>Total Time</key><integer>229176</integer>
              <key>Track Number</key><integer>1</integer>
              <key>Year</key><integer>2012</integer>
              <key>Date Added</key><date>2013-02-17T17:44:01Z</date>
              <key>Bit Rate</key><integer>1411</integer>
              <key>Sample Rate</key><integer>44100</integer>
              <key>Comments</key><string>Visit http://error404fl.bandcamp.com</string>
              <key>Play Count</key><integer>36</integer>
              <key>Play Date</key><integer>3495907680</integer>
              <key>Play Date UTC</key><date>2014-10-11T21:28:00Z</date>
              <key>Skip Count</key><integer>25</integer>
              <key>Skip Date</key><date>2014-10-29T22:11:25Z</date>
              <key>Artwork Count</key><integer>1</integer>
              <key>Persistent ID</key><string>C2C7A2CE88DF68D2</string>
              <key>Track Type</key><string>File</string>
              <key>Location</key><string>file:///song.m4a</string>
              <key>File Folder Count</key><integer>5</integer>
              <key>Library Folder Count</key><integer>1</integer>
          </dict>
  
      val subject = new Music(new Dict(xml))

      "Music files" should "allow access to correct fields" in {
          subject.name should be ("Never Give Up")
          subject.artist should be ("Error404")
          subject.album should be ("Beta EP")
          subject.albumArtist should be ("")
          subject.size.isDefined should be (true)
          subject.size.get should be (31350039)

          subject.kind.isDefined should be (true)
          subject.kind.get should be ("Apple Lossless audio file")

          subject.isLocal should be (true)
          subject.isPurchased should be (false)
          subject.isProtected should be (false)

          subject.bitRate should be (1411)
          subject.sampleRate should be (44100)

          subject.playCount should be (36)
          subject.skipCount should be (25)

          subject.dateModified should be (subject.dateAdded)

          subject.year.isDefined should be (true)
          subject.year.get should be (2012)
      }
}
