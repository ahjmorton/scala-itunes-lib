package com.ahjmorton.itunesLib

abstract class Track(dict:Dict) {
     
}

case class Music(dict:Dict) extends Track(dict)
case class Podcast(dict:Dict) extends Track(dict)
case class Audiobook(dict:Dict) extends Track(dict)
case class TVShow(dict:Dict) extends Track(dict)
case class Movie(dict:Dict) extends Track(dict)
case class ITunesU(dict:Dict) extends Track(dict)
