package com.ahjmorton.itunesLib

import scala.xml.XML
import scala.xml.Node
import scala.xml.Elem
import scala.xml.Utility

object ITunesLib {

    def fromFile(path:String):ITunesLib = newLib(XML.loadFile(path))

    def create(xmlNode:Elem):ITunesLib = newLib(xmlNode)

    private def newLib(node:Node) = new DictITunesLib(new Dict(sanityCheck(node)))
        
    private def sanityCheck(rawNode:Node) = {
        val node = Utility.trim(rawNode)
        if(node.label == "dict") {
            node
        } else if (node.label == "plist" && node.child(0).label == "dict") {
            node.child(0)
        } else {
            throw new IllegalArgumentException("Unknown XML submited for generating ITunesLib :" + node)
        }
    }

    trait ITunesLib {
        def all:Iterable[Track]
        def music:Iterable[Music]
        def podcasts:Iterable[Podcast]
        def tvShows:Iterable[TVShow]
        def movies:Iterable[Movie]
        def audioBooks:Iterable[Audiobook]
    }

    class DictITunesLib(root:Dict) extends ITunesLib {
     
        def all = throw new UnsupportedOperationException("Not implemented yet")
        def music = playlistDicts("Music").map(new Music(_))
        def podcasts = playlistDicts("Podcasts").map(new Podcast(_))
        def tvShows = throw new UnsupportedOperationException("Not implemented yet")
        def movies = playlistDicts("Movies").map(new Movie(_))
        def audioBooks = playlistDicts("Audiobooks").map(new Audiobook(_))

        private val tracks = root.getDict("Tracks")

        private def playlistDicts(name:String) = playlist(name).map(tracks.getDict(_))

        private def playlist(name:String):Iterable[String] = {
            val playlist = 
                 root.getArrayOfDicts("Playlists")
                     .find((dict:Dict) => dict.getString("Name") == name) match {
                         case Some(playlist) => playlist 
                         case None => throw new IllegalArgumentException("Cannot find playlist with name " + name)
                     }
            playlist.getArrayOfDicts("Playlist Items").map(_.getInt("Track ID").toString)
        }
       

    }

}
