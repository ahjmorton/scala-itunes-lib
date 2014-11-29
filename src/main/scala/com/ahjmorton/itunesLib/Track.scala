package com.ahjmorton.itunesLib

import org.joda.time.DateTime

trait DictTrack {
     protected val root:Dict
}

trait TrackLike extends DictTrack{

     def name:String = root.getString("Name").get
     def location:String = root.getString("Location").get
     def trackType:String = root.getString("Track Type").get
     def dateAdded:DateTime = root.getDateTime("Date Added").get
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

     def size:Option[Int] = root.getInt("Size")

     def kind:Option[String] = root.getString("Kind")
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
     def diskNumber:Option[Int] = root.getInt("Disk Number")
     def diskCount:Option[Int] = root.getInt("Disk Count")
     def trackNumber:Option[Int] = root.getInt("Track Number")
     def trackCount:Option[Int] = root.getInt("Track Count")
     def year:Option[Int] = root.getInt("Year")
}

abstract class Track(dict:Dict) extends TrackLike {
     protected override val root = dict
}

case class Music(dict:Dict) extends Track(dict)
                               with AudioLike
                               with MayHaveTrackInfo

case class Podcast(dict:Dict) extends Track(dict)
                               with AudioLike
                               with VideoLike

case class Audiobook(dict:Dict) extends Track(dict)
                               with AudioLike
                               with MayHaveTrackInfo

case class TVShow(dict:Dict) extends Track(dict)
                               with AudioLike
                               with VideoLike
                               with MayHaveTrackInfo {
     def series:String = root.getString("Series").get
     def episode:String = root.getString("Episode").get
     def season:Option[Int] = root.getInt("Season")
}

case class Movie(dict:Dict) extends Track(dict)
                               with AudioLike
                               with VideoLike
                               with MayHaveTrackInfo

case class ITunesU(dict:Dict) extends Track(dict)
                               with AudioLike
                               with VideoLike
