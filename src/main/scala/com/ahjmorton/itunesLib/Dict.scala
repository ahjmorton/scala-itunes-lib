package com.ahjmorton.itunesLib

import scala.xml.Node

class Dict(xml:Node) {

    private val index = (xml \ "_").grouped(2).map {
        case Seq(keyNode, elementNode) => (keyNode.text, elementNode)
    }.toMap

    private def findAndConvert[T](key:String, conv:(Node => T)) : Option[T] = {
        if(index contains key) {
            Some(conv(index(key)))        
        } else {
            None
        }
    }

    def getString = findAndConvert(_:String, (node) => node.text)

    def getInt = findAndConvert(_:String, (node) => node.text.toInt)

    def getBoolean = findAndConvert(_:String, (node) => node.label == "true")

    def getDict = findAndConvert(_:String, (node) => new Dict(node)) 

    def getArrayOfDicts = findAndConvert(_:String, 
         (node) => {
             (node \ "_").map(new Dict(_))
         }
    )
}
