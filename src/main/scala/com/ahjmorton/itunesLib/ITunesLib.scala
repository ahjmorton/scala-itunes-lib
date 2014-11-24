package com.ahjmorton.itunesLib

import scala.xml.XML
import scala.xml.Node
import scala.xml.Elem

object ITunesLib {

    def fromFile(path:String) = newLib(XML.loadFile(path))

    def create(xmlNode:Elem) = newLib(xmlNode)

    private def newLib(node:Node) = new ITunesLib(new Dict(sanityCheck(node)))
        
    private def sanityCheck(node:Node) = {
        if(node.label == "dict") {
            node
        } else if (node.label == "plist" && node.child(0).label == "dict") {
            node.child(0)
        } else {
            throw new IllegalArgumentException("Unknown XML submited for generating ITunesLib :" + node)
        }
    }

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
