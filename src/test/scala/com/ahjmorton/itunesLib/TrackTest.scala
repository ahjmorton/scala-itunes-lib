package com.ahjmorton.itunesLib

import org.joda.time.DateTime

import org.scalatest._

class TrackFileTypeTest extends FlatSpec with Matchers {

      val musicXML = 
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
          val music = new Music(new Dict(musicXML))
          music.trackId should be (1)
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

    val audioBookXML = 
    <dict>
        <key>Track ID</key><integer>2</integer>
        <key>Name</key><string>Mogworld (Unabridged) Part 1</string>
        <key>Artist</key><string>Yahtzee Croshaw</string>
        <key>Album Artist</key><string>Yahtzee Croshaw</string>
        <key>Album</key><string>Mogworld (Unabridged)</string>
        <key>Genre</key><string>Sci Fi &#38; Fantasy</string>
        <key>Kind</key><string>Protected AAC audio file</string>
        <key>Size</key><integer>187400837</integer>
        <key>Total Time</key><integer>23569644</integer>
        <key>Disc Number</key><integer>1</integer>
        <key>Disc Count</key><integer>1</integer>
        <key>Track Number</key><integer>1</integer>
        <key>Track Count</key><integer>2</integer>
        <key>Date Modified</key><date>2012-11-07T07:55:35Z</date>
        <key>Date Added</key><date>2012-11-07T07:54:11Z</date>
        <key>Bit Rate</key><integer>64</integer>
        <key>Sample Rate</key><integer>22050</integer>
        <key>Play Count</key><integer>1</integer>
        <key>Play Date</key><integer>3435729106</integer>
        <key>Play Date UTC</key><date>2012-11-14T09:11:46Z</date>
        <key>Artwork Count</key><integer>1</integer>
        <key>Persistent ID</key><string>5BF51A580DB9EB16</string>
        <key>Track Type</key><string>File</string>
        <key>Protected</key><true/>
        <key>Purchased</key><true/>
        <key>Location</key><string>file:///audiobook.m4b</string>
        <key>File Folder Count</key><integer>4</integer>
        <key>Library Folder Count</key><integer>1</integer>
    </dict>
    
    "Audiobook podcast files" should "allow access to correct fields" in {
        val audiobook = new Audiobook(new Dict(audioBookXML))

        audiobook.trackId should be (2)

        audiobook.isPurchased should be (true)
        audiobook.isProtected should be (true)

        audiobook.discNumber.isDefined should be (true)
        audiobook.discNumber.get should be (1)

        audiobook.discCount.isDefined should be (true)
        audiobook.discCount.get should be (1)

        audiobook.trackNumber.isDefined should be (true)
        audiobook.trackNumber.get should be (1)

        audiobook.trackCount.isDefined should be (true)
        audiobook.trackCount.get should be (2)
        
    }

    val tvShowXML = 
    <dict>
        <key>Track ID</key><integer>3</integer>
        <key>Name</key><string>Who Would Win / Lady &#38; Peebles</string>
        <key>Artist</key><string>Adventure Time</string>
        <key>Album Artist</key><string>Adventure Time</string>
        <key>Album</key><string>Adventure Time</string>
        <key>Genre</key><string>Kids</string>
        <key>Kind</key><string>Protected MPEG-4 video file</string>
        <key>Size</key><integer>221310914</integer>
        <key>Total Time</key><integer>1286920</integer>
        <key>Disc Number</key><integer>1</integer>
        <key>Disc Count</key><integer>1</integer>
        <key>Track Number</key><integer>6</integer>
        <key>Year</key><integer>2012</integer>
        <key>Date Modified</key><date>2014-09-28T13:36:03Z</date>
        <key>Date Added</key><date>2014-09-28T13:26:37Z</date>
        <key>Bit Rate</key><integer>121</integer>
        <key>Release Date</key><date>2012-11-05T08:00:00Z</date>
        <key>Artwork Count</key><integer>1</integer>
        <key>Series</key><string>Adventure Time</string>
        <key>Episode</key><string>49</string>
        <key>Episode Order</key><integer>6</integer>
        <key>Sort Album</key><string>Adventure Time, Vol. 5</string>
        <key>Persistent ID</key><string>0C0444F9A181FA4E</string>
        <key>Track Type</key><string>File</string>
        <key>Protected</key><true/>
        <key>Purchased</key><true/>
        <key>Has Video</key><true/>
        <key>HD</key><false/>
        <key>Video Width</key><integer>640</integer>
        <key>Video Height</key><integer>478</integer>
        <key>TV Show</key><true/>
        <key>Location</key><string>file:///tv_show.m4v</string>
        <key>File Folder Count</key><integer>4</integer>
        <key>Library Folder Count</key><integer>1</integer>
    </dict>

    "TV Show files" should "allow access to correct fields" in {
        val tvShow = new TVShow(new Dict(tvShowXML))
        
        tvShow.trackId should be (3)

        tvShow.hasVideo should be (true)
        tvShow.isHD should be (false)

        tvShow.episode should be ("49")
        tvShow.episodeOrder should be (6)
        tvShow.series should be ("Adventure Time")
        tvShow.season.isDefined should be (false)

        tvShow.videoWidth should be (640)
        tvShow.videoHeight should be (478)
    }

     val audioPodcastXML = 
     <dict>
        <key>Track ID</key><integer>4</integer>
        <key>Name</key><string>#233 4 Joey Diaz, Dean Delray and Lee Syatt</string>
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

          audioPodcast.trackId should be (4)

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
        <key>Track ID</key><integer>5</integer>
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
          videoPodcast.trackId should be (5)
          videoPodcast.hasVideo should be (true)
          videoPodcast.isHD should be (false)
          videoPodcast.videoWidth should be (640)
          videoPodcast.videoHeight should be (360)

          videoPodcast.artworkCount should be (1)
          videoPodcast.isExplicit should be (false)
     }

     val movieXML =
     <dict>
        <key>Track ID</key><integer>6</integer>
        <key>Name</key><string>The Godfather: The Coppola Restoration</string>
        <key>Artist</key><string>Francis Ford Coppola</string>
        <key>Genre</key><string>Drama</string>
        <key>Kind</key><string>Protected MPEG-4 video file</string>
        <key>Size</key><integer>6083053810</integer>
        <key>Total Time</key><integer>10624927</integer>
        <key>Year</key><integer>2008</integer>
        <key>Date Modified</key><date>2014-06-22T21:23:44Z</date>
        <key>Date Added</key><date>2014-06-22T20:59:32Z</date>
        <key>Bit Rate</key><integer>110</integer>
        <key>Play Count</key><integer>1</integer>
        <key>Play Date</key><integer>3487527962</integer>
        <key>Play Date UTC</key><date>2014-07-06T21:46:02Z</date>
        <key>Release Date</key><date>2008-10-27T07:00:00Z</date>
        <key>Artwork Count</key><integer>1</integer>
        <key>Sort Name</key><string>Godfather: The Coppola Restoration</string>
        <key>Persistent ID</key><string>80C9075E5FD06C11</string>
        <key>Content Rating</key><string>uk-movie|18|400|</string>
        <key>Track Type</key><string>File</string>
        <key>Protected</key><true/>
        <key>Purchased</key><true/>
        <key>Has Video</key><true/>
        <key>HD</key><true/>
        <key>Video Width</key><integer>1280</integer>
        <key>Video Height</key><integer>720</integer>
        <key>Movie</key><true/>
        <key>Location</key><string>file:///movie.m4v</string>
        <key>File Folder Count</key><integer>4</integer>
        <key>Library Folder Count</key><integer>1</integer>
     </dict>

     "Movie files" should "allow access to correct fields" in {
          val movie = new Movie(new Dict(movieXML))
          movie.trackId should be (6)
          movie.hasVideo should be (true)
          movie.isHD should be (true)
          movie.videoWidth should be (1280)
          movie.videoHeight should be (720)

          movie.artworkCount should be (1)

          movie.contentRating.isDefined should be (true)
          movie.contentRating.get should be ("uk-movie|18|400|")
     }
}
