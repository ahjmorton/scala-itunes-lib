package com.ahjmorton.itunesLib

import scala.xml.Node

import org.joda.time.DateTime

class Dict(xml:Node) {

    private val index = (xml \ "_").grouped(2).map {
        case Seq(keyNode, elementNode) => (keyNode.text, elementNode)
    }.toMap

    private def findAndConvert[T](key:String, 
                                  conv:(Node => T)) : Option[T] = 
         index.get(key).map(conv)

    def getString = findAndConvert(_:String, node => node.text)

    def getInt = findAndConvert(_:String, node => node.text.toInt)

    def getLong = findAndConvert(_:String, node => node.text.toLong)

    def getBoolean = findAndConvert(_:String, node => node.label == "true")

    def getDict = findAndConvert(_:String, node => new Dict(node)) 

    def getDateTime = findAndConvert(_:String, node => DateTime.parse(node.text))

    def getArrayOfDicts = findAndConvert(_:String, 
         node => {
             (node \ "_").map(new Dict(_))
         }
    )
}
