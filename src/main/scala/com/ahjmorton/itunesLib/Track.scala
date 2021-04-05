package com.ahjmorton.itunesLib

import java.time.ZonedDateTime

trait DictTrack {
     protected val root:Dict
}

trait TrackLike extends DictTrack{

     private def getOrFail[T](key:String, op:Option[T]):T = {
          op.getOrElse(throw new IllegalStateException("Expected value [" + key + "] to be present but not"))
     }

     protected def getStringOrFail(key:String) = getOrFail(key, root.getString(key))
     protected def getDateTimeOrFail(key:String) = getOrFail(key, root.getDateTime(key))
     protected def getNumberOrFail(key:String) = getOrFail(key, root.getNumber(key))

     def trackId:Long = getNumberOrFail("Track ID")
     def name:String = getStringOrFail("Name")
     def location:String = getStringOrFail("Location")
     def trackType:String = getStringOrFail("Track Type")
     def dateAdded:ZonedDateTime = getDateTimeOrFail("Date Added")
     def dateModified:ZonedDateTime = root.getDateTime("Date Modified").getOrElse(dateAdded)

     def playCount:Long = root.getNumber("Play Count").getOrElse(0)
     def skipCount:Long = root.getNumber("Skip Count").getOrElse(0)

     def isLocal:Boolean = trackType == "File"

     private def boolDefaultFalse(key:String) = root.getBoolean(key).getOrElse(false)
     def isPurchased:Boolean = boolDefaultFalse("Purchased")
     def isProtected:Boolean = boolDefaultFalse("Protected")
     def isExplicit:Boolean = boolDefaultFalse("Explicit")
     def isUnplayed:Boolean = boolDefaultFalse("Unplayed")

     def releaseDate:Option[ZonedDateTime] = root.getDateTime("Release Date")
     def size:Option[Long] = root.getNumber("Size")
     def kind:Option[String] = root.getString("Kind")
     def totalTime:Option[Long] = root.getNumber("Total Time")
     def year:Option[Long] = root.getNumber("Year")
}

trait AudioLike extends DictTrack {

     private def stringOrEmpty(key:String) = root.getString(key).getOrElse("")

     def artist:String = stringOrEmpty("Artist")
     def album:String = stringOrEmpty("Album")
     def composer:String = stringOrEmpty("Composer")
     def albumArtist:String = stringOrEmpty("Album Artist")
     def genre:String = stringOrEmpty("Genre")

     def sampleRate:Long = root.getNumber("Sample Rate").getOrElse(0)
     def bitRate:Long = root.getNumber("Bit Rate").getOrElse(0)
     def artworkCount:Long = root.getNumber("Artwork Count").getOrElse(0)
}

trait VideoLike extends DictTrack {
     def hasVideo:Boolean = root.getBoolean("Has Video").getOrElse(false)
     def isHD:Boolean = root.getBoolean("HD").getOrElse(false)
     def videoHeight:Long = root.getNumber("Video Height").getOrElse(0)
     def videoWidth:Long = root.getNumber("Video Width").getOrElse(0)
}

trait MayHaveTrackInfo extends DictTrack {
     def discNumber:Option[Long] = root.getNumber("Disc Number")
     def discCount:Option[Long] = root.getNumber("Disc Count")
     def trackNumber:Option[Long] = root.getNumber("Track Number")
     def trackCount:Option[Long] = root.getNumber("Track Count")
}

sealed abstract class Track(dict:Dict) extends TrackLike {
     protected override val root = dict
}

class Music(dict:Dict) extends Track(dict)
                               with AudioLike
                               with MayHaveTrackInfo

class Podcast(dict:Dict) extends Track(dict)
                               with AudioLike
                               with VideoLike

class Audiobook(dict:Dict) extends Track(dict)
                               with AudioLike
                               with MayHaveTrackInfo

class TVShow(dict:Dict) extends Track(dict)
                               with AudioLike
                               with VideoLike
                               with MayHaveTrackInfo {
     def series:String = getStringOrFail("Series")
     def episode:String = getStringOrFail("Episode")
     def episodeOrder:Long = getNumberOrFail("Episode Order")
     def season:Option[Long] = root.getNumber("Season")
}

class Movie(dict:Dict) extends Track(dict)
                               with AudioLike
                               with VideoLike
                               with MayHaveTrackInfo {
     def contentRating:Option[String] = root.getString("Content Rating")
}

class ITunesU(dict:Dict) extends Track(dict)
                               with AudioLike
                               with VideoLike
