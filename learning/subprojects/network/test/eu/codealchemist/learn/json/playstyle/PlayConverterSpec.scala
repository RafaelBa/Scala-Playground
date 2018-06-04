package eu.codealchemist.learn.json
package playstyle

import org.specs2.Specification
import play.api.libs.json.JsResultException

import eu.codealchemist.learn.json.playstyle.Conversions._
import Drinks._

class PlayConverterSpec extends Specification { def is =
"Play Json Lib".title ^ s2"""
  Play provides a functional like library to convert objects into JSON and vice versa.

  The converters work similiar for reading and writing. But if there is only one object which should be converted it will get a little bit complicated.
    Conversion to JSON is straigth forward                         $e1
    Conversion from JSON will
      yield the object if successfull                              $e2
      throw and Exception if it was not successfull                $e3

  A trait can here be encoded and decoded as well, again, the JSONs have not to be ambigious.
    Encoding both subclasses will result in each one object of the trait             $e4
    Decoding both subclasses will and getting the result as option will yield
      Some(drink) for a success                                    $e5
      None for a failure                                           $e6

  You also can step down with the syntax manually, which works fine.
    Encoding will yield a JSON again                               $e7
    Decoding is here wrapped around by a Either, which will yield a
      Right(whisky) for a success                                  $e8
      Left(exc) for a failure                                      $e9

  And a little proof that all codecs decode as they encode        $codecs
"""

  def e1 = cocktailToJson(caipi) should beEqualTo(caipiJson)
  def e2 = jsonToCocktail(caipiJson) should beEqualTo(caipi)
  def e3 = jsonToCocktail(erronousCocktailJson) should throwA[JsResultException]

  def e4 = drinkToJson(beer) should beEqualTo(beerJson) and (
    drinkToJson(wine) should beEqualTo(wineJson) )
  def e5 = jsonToDrink(beerJson) should beSome(beer) and (
    jsonToDrink(wineJson) should beSome(wine))
  def e6 = jsonToDrink(erronousDrinkJson) should beNone

  def e7 = whiskyToJson(ardbeg) should beEqualTo(ardbegJson)
  def e8 = jsonToWhisky(ardbegJson) should beRight(ardbeg)
  def e9 = jsonToWhisky(erronousWhiskyJson).left.map(_.getClass) should beLeft(classOf[JsResultException])  // TODO this can be done like beLeft(beTypedEqualTo(exc))

  def codecs = jsonToCocktail(cocktailToJson(caipi)) should beEqualTo(caipi) and(
    jsonToDrink(drinkToJson(beer)) should beSome(beer) and(
      jsonToDrink(drinkToJson(wine)) should beSome(wine) and(
        jsonToWhisky(whiskyToJson(ardbeg)) should beRight(ardbeg)
        )
      )
    )
}
