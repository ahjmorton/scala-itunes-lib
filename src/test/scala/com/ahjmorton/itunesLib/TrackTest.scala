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
  

      "Music files" should "allow access to correct fields" in {
          val music = new Music(new Dict(xml))
          music.name should be ("Never Give Up")
          music.artist should be ("Error404")
          music.album should be ("Beta EP")
          music.albumArtist should be ("")
          music.size.isDefined should be (true)
          music.size.get should be (31350039)

          music.totalTime.isDefined should be (true)
          music.totalTime.get should be (229176)

          music.kind.isDefined should be (true)
          music.kind.get should be ("Apple Lossless audio file")

          music.isLocal should be (true)
          music.isPurchased should be (false)
          music.isProtected should be (false)

          music.bitRate should be (1411)
          music.sampleRate should be (44100)

          music.playCount should be (36)
          music.skipCount should be (25)

          music.dateModified should be (music.dateAdded)

          music.year.isDefined should be (true)
          music.year.get should be (2012)
      }
}

class PodcastFileTest extends FlatSpec with Matchers {
     
     val audioPodcastXML = 
     <dict>
        <key>Name</key><string>#233 - Joey Diaz, Dean Delray and Lee Syatt</string>
        <key>Artist</key><string>Joey Diaz</string>
        <key>Composer</key><string>The Church Of What's Happening Now</string>
        <key>Album</key><string>The Church of What's Happening Now: With Joey Coco Diaz</string>
        <key>Genre</key><string>Podcast</string>
        <key>Kind</key><string>MPEG audio file</string>
        <key>Size</key><integer>97114602</integer>
        <key>Total Time</key><integer>8091794</integer>
        <key>Year</key><integer>2014</integer>
        <key>Date Modified</key><date>2014-11-26T19:58:07Z</date>
        <key>Date Added</key><date>2014-11-26T19:58:07Z</date>
        <key>Bit Rate</key><integer>96</integer>
        <key>Sample Rate</key><integer>44100</integer>
        <key>Release Date</key><date>2014-11-25T03:52:00Z</date>
        <key>Sort Album</key><string>Church of What's Happening Now: With Joey Coco Diaz</string>
        <key>Sort Composer</key><string>Church Of What's Happening Now</string>
        <key>Persistent ID</key><string>8281C538803BD218</string>
        <key>Explicit</key><true/>
        <key>Track Type</key><string>File</string>
        <key>Podcast</key><true/>
        <key>Unplayed</key><true/>
        <key>Location</key><string>file:///audio_podcast.m4a</string>
        <key>File Folder Count</key><integer>4</integer>
        <key>Library Folder Count</key><integer>1</integer>
     </dict>

     "Audio podcast files" should "allow access to correct fields" in {
          val audioPodcast = new Podcast(new Dict(audioPodcastXML))
          audioPodcast.hasVideo should be (false)

          audioPodcast.totalTime.isDefined should be (true)
          audioPodcast.totalTime.get should be (8091794)
          
          audioPodcast.isUnplayed should be (true)
          audioPodcast.isExplicit should be (true)
          audioPodcast.bitRate should be (96)
          audioPodcast.genre should be ("Podcast")

          audioPodcast.artist should be ("Joey Diaz")
          audioPodcast.album should be ("The Church of What's Happening Now: With Joey Coco Diaz")
     }

     val videoPodcastXML =
     <dict>
        <key>Track ID</key><integer>7209</integer>
        <key>Name</key><string>Episode 38 - Beatnik</string>
        <key>Album</key><string>Tiki Bar TV</string>
        <key>Genre</key><string>Podcast</string>
        <key>Kind</key><string>MPEG-4 video file</string>
        <key>Size</key><integer>48325147</integer>
        <key>Total Time</key><integer>318185</integer>
        <key>Year</key><integer>2008</integer>
        <key>Date Modified</key><date>2014-03-12T00:54:18Z</date>
        <key>Date Added</key><date>2014-03-12T00:54:18Z</date>
        <key>Bit Rate</key><integer>120</integer>
        <key>Release Date</key><date>2008-06-30T07:59:00Z</date>
        <key>Artwork Count</key><integer>1</integer>
        <key>Persistent ID</key><string>B3E048F325B177E9</string>
        <key>Track Type</key><string>File</string>
        <key>Podcast</key><true/>
        <key>Unplayed</key><true/>
        <key>Has Video</key><true/>
        <key>HD</key><false/>
        <key>Video Width</key><integer>640</integer>
        <key>Video Height</key><integer>360</integer>
        <key>Movie</key><true/>
        <key>Location</key><string>file:///vidoe_podcast.m4v</string>
        <key>File Folder Count</key><integer>4</integer>
        <key>Library Folder Count</key><integer>1</integer>
     </dict>

     "Video podcast files" should "allow access to correct fields" in {
          val videoPodcast = new Podcast(new Dict(videoPodcastXML))
          videoPodcast.hasVideo should be (true)
          videoPodcast.isHD should be (false)
          videoPodcast.videoWidth should be (640)
          videoPodcast.videoHeight should be (360)

          videoPodcast.artworkCount should be (1)
          videoPodcast.isExplicit should be (false)
     }
}
