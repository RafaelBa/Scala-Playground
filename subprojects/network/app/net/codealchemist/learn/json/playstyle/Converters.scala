package net.codealchemist.learn.json

import play.api.libs.json._
import play.api.libs.functional.syntax._

import scala.util.control.NonFatal

object Converters {
  implicit val ingredientWriter: Writes[Ingredient] = new Writes[Ingredient] {
    def writes(i: Ingredient) = Json.obj("ingredient" -> i.name)
  }

  implicit val ingredientReader: Reads[Ingredient] = (JsPath \ "ingredient").read[String].map(Ingredient(_))

  implicit val cocktailWriter: Writes[Cocktail] = (
    (JsPath \ "name").write[String] and
    (JsPath \ "ingredients").write[List[Ingredient]]
    )(unlift(Cocktail.unapply))

  implicit val cocktailReader: Reads[Cocktail] = (
    (JsPath \ "name").read[String] and
    (JsPath \ "ingredients").read[List[Ingredient]]
    )(Cocktail)

  implicit val beerWriter: Writes[Beer] = (
    (JsPath \ "beerName").write[String] and
    (JsPath \ "kind").write[String]
    )(unlift(Beer.unapply))

  implicit val beerReader: Reads[Beer] = (
    (JsPath \ "beerName").read[String] and
    (JsPath \ "kind").read[String]
    )(Beer)

  implicit val wineWriter: Writes[Wine] = (
    (JsPath \ "wineName").write[String] and
    (JsPath \ "kind").write[String]
    )(unlift(Wine.unapply))

  implicit val wineReader: Reads[Wine] = (
    (JsPath \ "wineName").read[String] and
    (JsPath \ "kind").read[String]
    )(Wine)

  implicit val drinkWriter: Writes[Drink] = new Writes[Drink] {
    def writes(d: Drink) = d match {
      case b: Beer => beerWriter.writes(b)
      case w: Wine => wineWriter.writes(w)
    }
  }

  implicit val drinkReader: Reads[Drink] = try {
    JsPath.read[Beer].map((d: Drink) => d)
  } catch {
    case NonFatal(e) => JsPath.read[Wine].map((d: Drink) => d)
  }

  implicit val whiskyWriter: Writes[Whisky] = (
    (JsPath \ "name").write[String] and
    (JsPath \ "taste" \ "body").write[String] and
    (JsPath \ "taste" \ "finish").write[String]
    )(unlift(pourWhisky))

  private lazy val pourWhisky = (w: Whisky) => Some((w.name, w.taste.body, w.taste.finish))

  implicit val whiskyReader: Reads[Whisky] = (
    (JsPath \ "name").read[String] and
    (JsPath \ "taste" \ "body").read[String] and
    (JsPath \ "taste" \ "finish").read[String]
    )(fillWhisky)

  private lazy val fillWhisky = (name: String, body: String, finish: String) => Whisky(name, Taste(body, finish))
}
