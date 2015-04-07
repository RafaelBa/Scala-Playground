package net.codealchemist.learn.json
package playcodecs

import play.api.libs.json.Json.{parse, stringify, toJson}

object Converters {
  def coffeeToJson(c: Coffee): String = stringify(toJson(c))

  def jsonToCoffee(s: String): Option[Coffee] = parse(s).asOpt[Coffee]

  // TODO make examples for usage of encoding and decoding of json with play
}