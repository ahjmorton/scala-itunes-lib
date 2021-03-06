package com.ahjmorton.itunesLib

import scala.xml.Node
import java.time.ZonedDateTime

class Dict(xml:Node) {

    private val index = (xml \ "_").grouped(2).map {
        case Seq(keyNode, elementNode) => (keyNode.text, elementNode)
    }.toMap

    private def findAndConvert[T](key:String, 
                                  conv:(Node => T)) : Option[T] = 
         index.get(key).map(conv)

    def getString = findAndConvert(_:String, node => node.text)

    def getNumber = findAndConvert(_:String, node => node.text.toLong)

    def getBoolean = findAndConvert(_:String, node => node.label == "true")

    def getDict = findAndConvert(_:String, node => new Dict(node)) 

    def getDateTime = findAndConvert(_:String, node => ZonedDateTime.parse(node.text))

    def getArrayOfDicts = findAndConvert(_:String, 
         node => {
             (node \ "_").map(new Dict(_))
         }
    )
}
