package com.ahjmorton.itunesLib

import scala.xml.XML
import scala.xml.Node
import scala.xml.Elem

object ITunesLib {

    private def newLib(node:Node) = new ITunesLib(new Dict(node))

    def fromFile(path:String) = newLib(XML.loadFile(path))

    def create(xmlNode:Elem) = newLib(xmlNode)
}

class ITunesLib(root:Dict) {
    
    private lazy val tracks = root.getDict("Tracks")

    def all = throw new UnsupportedOperationException("Not implemented yet")
    def music = throw new UnsupportedOperationException("Not implemented yet")
    def podcasts = throw new UnsupportedOperationException("Not implemented yet")
    def tvShows = throw new UnsupportedOperationException("Not implemented yet")
    def movies = throw new UnsupportedOperationException("Not implemented yet")
    def audioBooks = throw new UnsupportedOperationException("Not implemented yet")
}
