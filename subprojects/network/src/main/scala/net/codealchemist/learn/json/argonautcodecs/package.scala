package net.codealchemist.learn.json

import argonaut._
import Argonaut._

package object argonautcodecs {
  /**
   * This is the easiest way to define a codec.
   */
  implicit val caseCodecForBeans = casecodec2(Beans, Beans.unapply)("beanName", "origin")

  /**
   * This one needs the Beans to be defined before this one. But once they are defined you can use codecs in a cascading way.
   * This JSON will look like: {beans: {beansName: name, origin: origin}, brewingTime: 2, brewingTemperature: 100}
   */
  implicit val caseCodecForCoffee = casecodec3(Coffee, Coffee.unapply)("beans", "brewingTime", "brewingTemperature")

  /**
   * This is the manual way to define codecs, e.g. when you have to deal with classes.
   * The first one is a EncodeJson and the second one is a DecodeJson, both wrapped in a CocecJson.
   * Note, that the ->: means prepend.
   */
  implicit val classCodecForLeafs = CodecJson(
    (l: Leafs) => ("origin" := l.origin) ->: ("leafName" := l.name) ->: jEmptyObject,
    c => for {
      name <- (c --\ "leafName").as[String]
      origin <- (c --\ "origin").as[String]
    } yield Leafs(name, origin)
  )

  /**
   * Here, again, you can benefit from the cascading feature of Argonaut. in the EncodeJson as well as in the DecodeJson we can directly use the leafs.
   */
  implicit val classCodecForTea = CodecJson(
    (t: Tea) => ("brewingTemperature" := t.brewingTemperature) ->: ("brewingTime" := t.brewingTime) ->: ("leafs" := t.leafs) ->: jEmptyObject,
    c => for {
      leafs <- (c --\ "leafs").as[Leafs]
      time <- (c --\ "brewingTime").as[Int]
      temperature <- (c --\ "brewingTemperature").as[Int]
    } yield Tea(leafs, time, temperature)
  )

  /**
   * This one is a bit tricky:
   * You want to decode an encode a trait, which in fact cannot be instatiated, which means, you necessarily need classes and therefore codecs for these classes.
   * Furthermore important is, that the codecs have at least one different key, so that the objects can be distinguished.
   * The decoder also needs to cast the object to the type of the trait, as the decoder is not covariant for the sake of decoding the trait into the correct type.
   */
  implicit val combinedTraitCodecForHotDrinks: CodecJson[HotDrink] = CodecJson(
    (h: HotDrink) => h match {
      case c: Coffee => caseCodecForCoffee.encode(c)
      case t: Tea => classCodecForTea.encode(t)
    },
    c => caseCodecForCoffee.map((h: HotDrink) => h).decode(c) ||| classCodecForTea.map((h: HotDrink) => h).decode(c)
  )

  /**
   * This example shows you, that you can encode and decode the JSON as you like, so you can either srew it up pretty bad or use the
   * this flexibility to your advantage. You really should not do this often, only if necessary.
   */
  implicit val encodeBeamsAsLeafs: EncodeJson[Beans] = EncodeJson((b: Beans) => ("origin" := b.origin) ->: ("leafName" := b.name) ->: jEmptyObject)
  implicit val decodeBeansAsLeafs: DecodeJson[Leafs] = DecodeJson(c => for (name <- (c --\ "beanName").as[String]; origin <- (c --\ "origin").as[String]) yield Leafs(name, origin))

  /**
   * This is the manual way to encode an object as JSON. This way is disencouraged. Nevertheless, her you can see how it could be done.
   * Furthermore you can see here the usage of the Json object, which, by the way, appends the objects insteand of prepending them, like the ->: would do.
   * Note that the := does an implicit conversion of the given data to a JSON type (with '(key, value)' you would need somthing like '(key, jString(value))'.
   */
  implicit val manaulCoffeeEncode: EncodeJson[Coffee] = EncodeJson((c: Coffee) =>
    Json("beans" := Json("beanName" := c.beans.name, "origin" := c.beans.origin), "brewingTime" := c.brewingTime, "brewingTemperature" := c.brewingTemperature)
  )

  /**
   * And this is the manual way to decode a JSON string. As you can see you can use the --\ operator to step down, but nevertheless this is also discouraged.
   */
  implicit val manualCoffeeDecode: DecodeJson[Coffee] = DecodeJson(c => for {
    name <- (c --\ "beans" --\ "beanName").as[String]
    origin <- (c --\ "beans" --\ "origin").as[String]
    time <- (c --\ "brewingTime").as[Int]
    temperature <- (c --\ "brewingTemperature").as[Int]
  } yield Coffee(Beans(name, origin), time, temperature))


}
