package com.ahjmorton.itunesLib

import org.scalatest._

class DictTest extends FlatSpec with Matchers {

     "An ITunes Dictionary" should "be able to extract a string" in {
         val xml = <dict><key>Key</key><string>Hello</string></dict>
         val dict = new Dict(xml)
         dict.getString("Key").get should be ("Hello")
     }

     it should "be able to extract an integer" in {
         val xml = <dict><key>Key</key><integer>123</integer></dict>
         val dict = new Dict(xml)
         dict.getInt("Key").get should be (123)
     }

     it should "be able to extract a boolean" in {
         val xml = <dict><key>Key</key><true /></dict>
         val dict = new Dict(xml)
         dict.getBoolean("Key").get should be (true)
     }

     it should "be able to extract another dictionary" in {
         val xml = <dict><key>Key</key><dict><key>Subkey</key><true /></dict></dict>
         val dict = new Dict(xml)
         dict.getDict("Key").isDefined should be (true)
         val subDict = dict.getDict("Key").get
         subDict.getBoolean("Subkey").get should be (true)
     }

     it should "be able to extract an array of dictionaries" in {
         val xml = <dict><key>Key</key><array><dict><key>Subkey</key><true /></dict></array></dict>
         val dict = new Dict(xml)
         dict.getArrayOfDicts("Key").isDefined should be (true)
         val subArray = dict.getArrayOfDicts("Key").get
         subArray should have length 1
         val subDict = subArray(0)
         subDict.getBoolean("Subkey").get should be (true)
     }

     it should "return None if a value is not present" in {
         val xml = <dict><key>Missing</key><string>Fake</string></dict>
         val dict = new Dict(xml)
         dict.getString("Key").isDefined should be (false)

     }
}
