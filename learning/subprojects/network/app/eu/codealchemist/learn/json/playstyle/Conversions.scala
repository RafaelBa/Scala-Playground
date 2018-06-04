package eu.codealchemist.learn.json
package playstyle

import scala.util.control.NonFatal
import scala.util.{Failure, Success, Try}

import play.api.libs.json.Json.{parse, stringify, toJson}

import Converters._

object Conversions {
  def cocktailToJson(c: Cocktail): String = stringify(toJson(c))
  def jsonToCocktail(s: String): Cocktail = parse(s).as[Cocktail]

  def drinkToJson(d: Drink): String = stringify(toJson(d))
  def jsonToDrink(s: String): Option[Drink] = parse(s).asOpt[Drink]

  def whiskyToJson(w: Whisky): String = stringify(toJson(w))
  def jsonToWhisky(s: String): Either[Throwable, Whisky] = Try(parse(s).as[Whisky]) match {
    case Success(w) => Right(w)
    case Failure(NonFatal(e)) => Left(e)
  }
}