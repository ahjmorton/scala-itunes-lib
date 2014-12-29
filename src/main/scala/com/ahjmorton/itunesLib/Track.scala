package com.ahjmorton.itunesLib

import org.joda.time.DateTime

trait DictTrack {
     protected val root:Dict
}

trait TrackLike extends DictTrack{

     private def getOrFail[T](key:String, op:Option[T]):T = {
          op.getOrElse(throw new IllegalStateException("Expected value [" + key + "] to be present but not"))
     }

     protected def getStringOrFail(key:String) = getOrFail(key, root.getString(key))
     protected def getDateTimeOrFail(key:String) = getOrFail(key, root.getDateTime(key))
     protected def getIntOrFail(key:String) = getOrFail(key, root.getInt(key))

     def trackId:Int = getIntOrFail("Track ID")
     def name:String = getStringOrFail("Name")
     def location:String = getStringOrFail("Location")
     def trackType:String = getStringOrFail("Track Type")
     def dateAdded:DateTime = getDateTimeOrFail("Date Added")
     def dateModified:DateTime = root.getDateTime("Date Modified").getOrElse(dateAdded)

     def playCount:Int = root.getInt("Play Count").getOrElse(0)
     def skipCount:Int = root.getInt("Skip Count").getOrElse(0)

     def isLocal:Boolean = trackType == "File"

     private def boolDefaultFalse(key:String) = root.getBoolean(key).getOrElse(false)
     def isPurchased:Boolean = boolDefaultFalse("Purchased")
     def isProtected:Boolean = boolDefaultFalse("Protected")
     def isExplicit:Boolean = boolDefaultFalse("Explicit")
     def isUnplayed:Boolean = boolDefaultFalse("Unplayed")

     def releaseDate:Option[DateTime] = root.getDateTime("Release Date")
     def size:Option[Long] = root.getLong("Size")
     def kind:Option[String] = root.getString("Kind")
     def totalTime:Option[Int] = root.getInt("Total Time")
     def year:Option[Int] = root.getInt("Year")
}

trait AudioLike extends DictTrack {

     private def stringOrEmpty(key:String) = root.getString(key).getOrElse("")

     def artist:String = stringOrEmpty("Artist")
     def album:String = stringOrEmpty("Album")
     def composer:String = stringOrEmpty("Composer")
     def albumArtist:String = stringOrEmpty("Album Artist")
     def genre:String = stringOrEmpty("Genre")

     def sampleRate:Int = root.getInt("Sample Rate").getOrElse(0)
     def bitRate:Int = root.getInt("Bit Rate").getOrElse(0)
     def artworkCount:Int = root.getInt("Artwork Count").getOrElse(0)
}

trait VideoLike extends DictTrack {
     def hasVideo:Boolean = root.getBoolean("Has Video").getOrElse(false)
     def isHD:Boolean = root.getBoolean("HD").getOrElse(false)
     def videoHeight:Int = root.getInt("Video Height").getOrElse(0)
     def videoWidth:Int = root.getInt("Video Width").getOrElse(0)
}

trait MayHaveTrackInfo extends DictTrack {
     def discNumber:Option[Int] = root.getInt("Disc Number")
     def discCount:Option[Int] = root.getInt("Disc Count")
     def trackNumber:Option[Int] = root.getInt("Track Number")
     def trackCount:Option[Int] = root.getInt("Track Count")
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
     def episodeOrder:Int = getIntOrFail("Episode Order")
     def season:Option[Int] = root.getInt("Season")
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
