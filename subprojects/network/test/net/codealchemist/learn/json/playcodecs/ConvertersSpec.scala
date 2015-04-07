package net.codealchemist.learn.json
package playcodecs

import net.codealchemist.learn.json.playcodecs.Converters._
import org.specs2.Specification

class ConvertersSpec extends Specification { def is =
"Play Json Lib".title ^ s2"""
  Play provides a library to process JSON object and convert Scala objects to JSON.

  Simple conversion of a case class forth and back.       $e1
"""


  def e1 = coffeeToJson(arabicaEthiopia.obj) should beEqualTo(arabicaEthiopia.json) and (
    jsonToCoffee(arabicaEthiopia.json) should beSome(arabicaEthiopia.obj) )
}
