package net.codealchemist.learn.json

import play.api.libs.json._
import play.api.libs.functional.syntax._

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
}
