package com.ahjmorton.itunesLib

import java.time.ZonedDateTime

import org.scalatest._

class DictTest extends FlatSpec with Matchers {

     "An ITunes Dictionary" should "be able to extract a string" in {
         val xml = <dict><key>Key</key><string>Hello</string></dict>
         val dict = new Dict(xml)
         dict.getString("Key").get should be ("Hello")
     }

     it should "be able to extract a number" in {
         val xml = <dict><key>Key</key><integer>3235018016</integer></dict>
         val dict = new Dict(xml)
         dict.getNumber("Key").get should be (3235018016L)
     }

     it should "be able to extract a boolean" in {
         val xml = <dict><key>Key</key><true /></dict>
         val dict = new Dict(xml)
         dict.getBoolean("Key").get should be (true)
     }

     it should "be able to extract a date" in {
         val expectedDate = ZonedDateTime.parse("2014-08-27T01:00:00Z")
         val xml = <dict><key>Key</key><date>2014-08-27T01:00:00Z</date></dict>
         val dict = new Dict(xml)
         dict.getDateTime("Key").get should be (expectedDate)
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
