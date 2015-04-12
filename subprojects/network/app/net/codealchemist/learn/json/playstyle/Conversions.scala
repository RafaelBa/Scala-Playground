package net.codealchemist.learn.json
package playstyle

import play.api.libs.json.Json.{parse, stringify, toJson}

import Converters._

import scala.util.{Failure, Success, Try}

object Conversions {
  def cocktailToJson(c: Cocktail): String = stringify(toJson(c))
  def jsonToCoffee(s: String): Cocktail = parse(s).as[Cocktail]

  def drinkToJson(d: Drink): String = stringify(toJson(d))
  def jsonToDrink(s: String): Option[Drink] = parse(s).asOpt[Drink]

  def whiskyToJson(w: Whisky): String = stringify(toJson(w))
  def jsonToWhisky(s: String): Either[Exception, Whisky] = Try(parse(s).asOpt[Whisky]) match {
    case Success(w: Whisky) => Right(w)
    case Failure(e: Exception) => Left(e)
  }

  def optionToJson(maybeValue: Option[String]): String = stringify(toJson(maybeValue))
  def jsonToOption(json: String): Option[String] = parse(json).as[Option[String]]

  def optionOptionToJson(maybeMaybeValue: Option[Option[String]]): String = stringify(toJson(maybeMaybeValue))
  def jsonToOptionOption(json: String): Option[Option[String]] = parse(json).as[Option[Option[String]]]

  def eitherToJson(e: Either[Int, String]): String = stringify(toJson(e))
  def jsonToEither(json: String): Either[Int, String] = parse(json).as[Either[Int, String]]

  def eitherEitherToJson(eitherEither: Either[Either[Int, String], Double]): String = stringify(toJson(eitherEither))
  def jsonToEitherEither(json: String): Either[Either[Int, String], Double] = parse(json).as[Either[Either[Int, String], Double]]
}