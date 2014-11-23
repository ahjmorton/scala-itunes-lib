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
    
    

}
