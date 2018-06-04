package eu.codealchemist.learn.json

import argonaut._
import Argonaut._

import Codecs._

object Conversions {

  def cocktailToJson(c: Cocktail): String = c.asJson.toString
  def jsonToCocktail(json: String): Either[Either[String, (String, CursorHistory)], Cocktail] = json.decode[Cocktail]

  def drinkToJson(d: Drink): String = d.asJson.toString
  def jsonToDrink(json: String): Either[String, Drink] = json.decodeEither[Drink]

  def whiskyToJson(w: Whisky): String = w.asJson.toString
  def jsonToWhisky(json: String): Option[Whisky] = json.decodeOption[Whisky]
}