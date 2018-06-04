package eu.codealchemist.learn.json

import argonaut._
import Argonaut._

object Codecs {
  implicit val ingredientsCodec: CodecJson[Ingredient] = casecodec1(Ingredient, Ingredient.unapply)("ingredient")
  implicit val cocktailCodec: CodecJson[Cocktail] = casecodec2(Cocktail, Cocktail.unapply)("name", "ingredients")

  implicit val beerCodec: CodecJson[Beer] = CodecJson((b: Beer) =>
    ("kindOfBeer" := b.kind) ->: ("name" := b.name) ->: jEmptyObject,
    c => for {
      name <- (c --\ "name").as[String]
      kind <- (c --\ "kindOfBeer").as[String]
    } yield Beer(name, kind)
  )

  implicit val wineCodec: CodecJson[Wine] = CodecJson((w: Wine) =>
    ("kindOfWine" := w.kind) ->: ("name" := w.name) ->: jEmptyObject,
    c => for {
      name <- (c --\ "name").as[String]
      kind <- (c --\ "kindOfWine").as[String]
    } yield Wine(name, kind)
  )

  implicit val iceCodec: CodecJson[Drink] = CodecJson( (drink: Drink) =>
    drink match {
      case b: Beer => beerCodec.encode(b)
      case w: Wine => wineCodec.encode(w)
    },
    c => beerCodec.map((d: Drink) => d).decode(c) ||| wineCodec.map((d: Drink) => d).decode(c)
  )

  implicit val whiskyCodec: CodecJson[Whisky] = CodecJson((w: Whisky) =>
    Json("name" := w.name, "taste" := Json("body" := w.taste.body, "finish" := w.taste.finish)),
    c => for {
      name <- (c --\ "name").as[String]
      sortOf <- (c --\ "taste" --\ "body").as[String]
      basic <- (c --\ "taste" --\ "finish").as[String]
  } yield Whisky(name, Taste(sortOf, basic)))
}

