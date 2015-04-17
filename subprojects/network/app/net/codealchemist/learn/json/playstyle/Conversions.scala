package net.codealchemist.learn.json
package playstyle

import play.api.libs.json.Json.{parse, stringify, toJson}

import Converters._

import scala.util.{Failure, Success, Try}

object Conversions {
  def cocktailToJson(c: Cocktail): String = stringify(toJson(c))
  def jsonToCocktail(s: String): Cocktail = parse(s).as[Cocktail]

  def drinkToJson(d: Drink): String = stringify(toJson(d))
  def jsonToDrink(s: String): Option[Drink] = parse(s).asOpt[Drink]

  def whiskyToJson(w: Whisky): String = stringify(toJson(w))
  def jsonToWhisky(s: String): Either[Exception, Whisky] = Try(parse(s).asOpt[Whisky]) match {
    case Success(w: Whisky) => Right(w)
    case Failure(e: Exception) => Left(e)
  }
}