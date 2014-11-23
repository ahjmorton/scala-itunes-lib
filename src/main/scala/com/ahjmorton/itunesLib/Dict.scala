package com.ahjmorton.itunesLib

import scala.xml.Node

class Dict(xml:Node) {

    private val index = (xml \ "_").grouped(2).map {
        case Seq(keyNode, elementNode) => (keyNode.text, elementNode)
    }.toMap

    private def findAndConvert[T >: Null](key:String, conv:(Node => T)) : T = {
        if(index contains key) {
            conv(index(key)) 
        } else {
            null
        }
    }

    def getString = findAndConvert(_:String, (node) => node.text)

    def getInt = findAndConvert(_:String, (node) => node.text.toInt) match { 
        case a:Int => a;
        case _ => 0
    }

    def getBoolean = findAndConvert(_:String, (node) => node.label == "true") match { 
         case a:Boolean => a 
         case _ => false
    }

    def getDict = findAndConvert(_:String, (node) => new Dict(node)) 

    def getArrayOfDicts = findAndConvert(_:String, 
         (node) => {
             (node \ "_").map(new Dict(_))
         }
    )
}
