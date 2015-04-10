package net.codealchemist.learn.json
package argonautstyle

import argonaut._
import Argonaut._

object Converters {
  def coffeeToJson(c: Coffee): String = c.asJson.toString
  def teaToJson(t: Tea): String = t.asJson.toString

  def jsonToCoffee(s: String): Option[Coffee] = s.decodeOption[Coffee]
  // def jsonToTea(s: String)
  // TODO show the usge of the codecs
}