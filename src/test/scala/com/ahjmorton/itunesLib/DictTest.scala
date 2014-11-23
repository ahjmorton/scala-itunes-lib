package com.ahjmorton.itunesLib

import org.scalatest._

class DictTest extends FlatSpec with Matchers {

     "An ITunes Dictionary" should "be able to extract a string" in {
         val xml = <dict><key>Key</key><string>Hello</string></dict>
         val dict = new Dict(xml)
         dict.getString("Key") should be ("Hello")
     }

     it should "be able to extract an integer" in {
         val xml = <dict><key>Key</key><integer>123</integer></dict>
         val dict = new Dict(xml)
         dict.getInt("Key") should be (123)
     }

     it should "be able to extract a boolean" in {
         val xml = <dict><key>Key</key><true /></dict>
         val dict = new Dict(xml)
         dict.getBoolean("Key") should be (true)
     }

     it should "be able to extract another dictionary" in {
         val xml = <dict><key>Key</key><dict><key>Subkey</key><true /></dict></dict>
         val dict = new Dict(xml)
         dict.getDict("Key") should not be (null)
         val subDict = dict.getDict("Key")
         subDict.getBoolean("Subkey") should be (true)
     }

     it should "be able to extract an array of dictionaries" in {
         val xml = <dict><key>Key</key><array><dict><key>Subkey</key><true /></dict></array></dict>
         val dict = new Dict(xml)
         dict.getArrayOfDicts("Key") should not be (null)
         val subArray = dict.getArrayOfDicts("Key")
         subArray should have length 1
         val subDict = subArray(0)
         subDict.getBoolean("Subkey") should be (true)
     }
}
