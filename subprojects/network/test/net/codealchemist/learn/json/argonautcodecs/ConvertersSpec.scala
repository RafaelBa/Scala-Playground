package net.codealchemist.learn.json
package argonautcodecs

import org.specs2.Specification

import Converters._

class ConvertersSpec extends Specification { def is =
"Argonaut".title ^ s2"""
  Argonaut proiveds different ways to transform JSONs into objects and vice versa using a functional approach.

  Argonaut has a simple way to create codecs for case classes. $e1

"""
  // TODO make some tests to ensure, that the codecs work
  def e1 = coffeeToJson(arabicaEthiopia.obj) should beEqualTo(arabicaEthiopia.json) and (
    jsonToCoffee(arabicaEthiopia.json) should beSome(arabicaEthiopia.obj) )
}
