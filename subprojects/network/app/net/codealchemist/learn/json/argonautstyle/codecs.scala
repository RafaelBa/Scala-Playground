package net.codealchemist.learn.json

import argonaut._
import Argonaut._

object CaseCodecs {
  import ImportCodecs.ice

  implicit val ingredientsCodec: CodecJson[Ingredient] = casecodec1(Ingredient, Ingredient.unapply)("ingredient")
  implicit val cocktailCodec: CodecJson[Cocktail] = casecodec3(Cocktail, Cocktail.unapply)("name", "ingredients", "ice")
}

object ClassCodecs {
  import ImportCodecs.{ crushed, cubed }

  implicit val iceCodec: CodecJson[Ice] = CodecJson( (ice: Ice) =>
    ice match {
      case cr: Crushed => crushed.encode(cr)
      case cu: Cubed => cubed.encode(cu)
    },
    c => crushed.map((i: Ice) => i).decode(c) ||| cubed.map((i: Ice) => i).decode(c)
  )

  implicit val ingredientCodec: CodecJson[Ingredient] = CodecJson( (i: Ingredient) =>
    ("name" := i.name) ->: jEmptyObject,
    c => for {
      name <- (c --\ "name").as[String]
    } yield Ingredient(name)
  )

  implicit val cocktailCodec: CodecJson[Cocktail] = CodecJson( (c: Cocktail) =>
    ("ice" := c.ice) ->: ("ingredients" := c.ingredients) ->: ("name" := c.name) ->: jEmptyObject,
    c => for {
      name        <- (c --\ "name").as[String]
      ingredients <- (c --\ "ingredients").as[List[Ingredient]]
      ice         <- (c --\ "ice").as[Option[Ice]]
    } yield Cocktail(name, ingredients, ice)
  )
}

object ManualCodecs {
  implicit val cocktailInformationCodec: CodecJson[CocktailTaste] = CodecJson((c: CocktailTaste) =>
    Json("name" := c.name, "taste" := Json("sortOf" := c.taste.sortOf, "basic" := c.taste.basic)),
    c => for {
      name <- (c --\ "name").as[String]
      sortOf <- (c --\ "taste" --\ "sortOf").as[String]
      basic <- (c --\ "taste" --\ "basic").as[String]
  } yield CocktailTaste(name, Taste(sortOf, basic)))
}

object ImportCodecs {
  import ClassCodecs.iceCodec

  implicit val crushed = casecodec1(Crushed, Crushed.unapply)("crushedAmount")
  implicit val cubed = casecodec1(Cubed, Cubed.unapply)("cubedAmount")
  implicit val ice = iceCodec
}