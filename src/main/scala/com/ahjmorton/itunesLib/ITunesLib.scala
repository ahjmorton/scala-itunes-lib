package com.ahjmorton.itunesLib

import scala.xml.XML
import scala.xml.Node
import scala.xml.Elem
import scala.xml.Utility

trait ITunesLib {
    def music:Iterable[Music]
    def podcasts:Iterable[Podcast]
    def tvShows:Iterable[TVShow]
    def movies:Iterable[Movie]
    def audioBooks:Iterable[Audiobook]
    def itunesU:Iterable[ITunesU]
}

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



    private class DictITunesLib(root:Dict) extends ITunesLib {
     
        def music = playlistDicts("Music").map(Music)
        def podcasts = playlistDicts("Podcasts").map(Podcast)
        def tvShows = playlistDicts("TV Shows").map(TVShow)
        def movies = playlistDicts("Movies").map(Movie)
        def audioBooks = playlistDicts("Audiobooks").map(Audiobook)
        def itunesU = playlistDicts("iTunes U").map(ITunesU)

        private val tracks = root.getDict("Tracks").getOrElse(
          throw new IllegalArgumentException("Tracks element is missing from iTunesXML")
        )

        private val playlists = root.getArrayOfDicts("Playlists").getOrElse(
          throw new IllegalArgumentException("Playlists element is missing from iTunesXML")
        )

        private def playlistDicts(name:String) = {
             playlist(name)
               .map((trackId) => tracks.getDict(trackId).getOrElse(
                   throw new IllegalStateException("Track ID " + trackId + " specified in playlist but cannot find in list of tracks")
               ))
        }

        private def playlist(name:String):Iterable[String] = {
            val playlistOption = playlists.find((dict:Dict) =>
               dict.getString("Name").getOrElse(
                 throw new IllegalStateException("Playlist [" + name + "] has no Name element")
               ) == name
            )

            val playlist = playlistOption.getOrElse(
              throw new IllegalArgumentException("Cannot find playlist with name [ " + name + "]")
            )

            val playlistItems = playlist.getArrayOfDicts("Playlist Items").getOrElse(
              throw new IllegalStateException("Playlist [" + name + "] does not have a Playlist Items element")
            )

            playlistItems.map((playlistItemDict) => 
               playlistItemDict.getInt("Track ID").getOrElse(
                  throw new IllegalStateException(
                    "Track id missing in playlist item for playlist [" + name + "]: " + playlistItemDict
                  )
               ).toString
            )
        }
    }
}
