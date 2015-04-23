package net.codealchemist.learn.json
package playstyle

import org.specs2.Specification

import net.codealchemist.learn.json.playstyle.Conversions._
import Drinks._

class PlayConverterSpec extends Specification { def is =
"Play Json Lib".title ^ s2"""
  Play provides a functional like library to convert objects into JSON and vice versa.

  The converters work similiar for reading and writing. But if there is only one object which should be converted it will get a little bit complicated.
    Conversion to JSON is straigth forward                         $e1
    Conversion from JSON will
      yield the object if successfull                              $e2
      throw and Exception if it was not successfull                $e3

  Trait Drink
    Encoding bot subclasses               $e4
    Decoding both subclasses
      Some(drink)                         $e5
      None                                $e6

  Whisky, manual step down
    Encoding                              $e7
    Decoding
      Right(whisky)                       $e8
      Left(exc)                           $e9

  And a little proof that all codecs decode as they encode        $codecs
"""

  def e1 = cocktailToJson(caipi) should beEqualTo(caipiJson)
  def e2 = jsonToCocktail(caipiJson) should beEqualTo(caipi)
  def e3 = jsonToCocktail(erronousCocktailJson) should throwA[ClassCastException]

  def e4 = drinkToJson(beer) should beEqualTo(beerJson) and (
    drinkToJson(wine) should beEqualTo(wineJson) )
  def e5 = jsonToDrink(beerJson) should beSome(beer) and (
    jsonToDrink(wineJson) should beSome(wine))
  def e6 = jsonToDrink(erronousDrinkJson) should beNone

  def e7 = whiskyToJson(ardbeg) should beEqualTo(ardbegJson)
  def e8 = jsonToWhisky(ardbegJson) should beRight(ardbeg)
  def e9 = jsonToWhisky(erronousWhiskyJson) should beLeft(typedEqualExpectation(classOf[ClassCastException]))

  def codecs = jsonToCocktail(cocktailToJson(caipi)) should beEqualTo(caipi) and(
    jsonToDrink(drinkToJson(beer)) should beSome(beer) and(
      jsonToDrink(drinkToJson(wine)) should beSome(wine) and(
        jsonToWhisky(whiskyToJson(ardbeg)) should beRight(ardbeg)
        )
      )
    )
}
