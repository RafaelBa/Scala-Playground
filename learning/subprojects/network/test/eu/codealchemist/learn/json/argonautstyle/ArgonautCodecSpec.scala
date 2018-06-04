package eu.codealchemist.learn.json
package argonautstyle


import argonaut.{CursorOpDownField, CursorOp, CursorHistory}
import org.specs2.Specification
import scalaz.{-\/, \/-}

import Conversions._
import Drinks._

class ArgonautCodecSpec extends Specification { def is =
"Argonaut".title ^ s2"""
  Argonaut proiveds different ways to transform JSONs into objects and vice versa using a functional approach.

  CaseCodecs of argonaut create symmetric Encoders and Decoders.
     Encoding just works straight forward                                      $e1
     Decoders result in \/[\/[String, (String, CursorHistory)], T]
       with a Right[Right[T]] if the decoding was successful                   $e2
       with a Left[Right[(String, CursorHistory)]] including a message and the history if the decoding failed   $e3

  Classes have to be encoded and decoded in a more manual way.
    Encoding works fine and straight forward if there are codecs for the to be encoded subclasses               $e4
    There is also a decode with Either result for all decoders with
      Right[T] result for a successful decoding als long as there are decoders provided for the subclasses      $e5
      Left[String] with a error message for a failed decoding                  $e6

  There also is the possibility to make complete custom Encoders and Decoders.
    Again, encoding is easy                                                    $e7
    Decoding can also be chosen to return an Option[T] giving a
      Some(t) for success                                                      $e8
      None for a failed decoding                                               $e9

  And a little proof that all codecs decode as they encode                     $codecs
"""

  def e1 = cocktailToJson(caipi) should beEqualTo(caipiJson)
  def e2 = jsonToCocktail(caipiJson) should beEqualTo(\/-(caipi))
  def e3 = jsonToCocktail(erronousCocktailJson) should beEqualTo(
    -\/(\/-(
      ("Attempt to decode value on failed cursor.",
        CursorHistory(List(
          CursorOp.failedOp(CursorOpDownField("ingredients"))
        ))
      )
    )))

  def e4 = drinkToJson(beer) should beEqualTo(beerJson) and( drinkToJson(wine) should beEqualTo(wineJson) )
  def e5 = jsonToDrink(beerJson) should beRight(beer) and( jsonToDrink(wineJson) should beRight(wine))
  def e6 = {
    jsonToDrink(erronousDrinkJson) should beLeft("Attempt to decode value on failed cursor.: [*.--\\(kindOfWine)]")
  }

  def e7 = whiskyToJson(ardbeg) should beEqualTo(ardbegJson)
  def e8 = jsonToWhisky(ardbegJson) should beSome(ardbeg)
  def e9 = jsonToWhisky(erronousWhiskyJson) should beNone

  def codecs = jsonToCocktail(cocktailToJson(caipi)) should beEqualTo(\/-(caipi)) and(
    jsonToDrink(drinkToJson(beer)) should beRight(beer) and(
      jsonToDrink(drinkToJson(wine)) should beRight(wine) and(
        jsonToWhisky(whiskyToJson(ardbeg)) should beSome(ardbeg)
      )
    )
  )
}
