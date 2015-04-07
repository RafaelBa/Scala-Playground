package net.codealchemist.learn.json

import play.api.libs.json._
import play.api.libs.functional.syntax._

package object playcodecs {
  /**
   * The you write Writes for case classes.
   */
  implicit val beansWrites: Writes[Beans] = (
    (JsPath \ "beanName").write[String] and
    (JsPath \ "origin").write[String]
  )(unlift(Beans.unapply))

  /**
   * This is the way of Writes for normal classes
   */
  implicit val leafsWrites: Writes[Leafs] = (
    (JsPath \ "leafName").write[String] and
    (JsPath \ "origin").write[String]
  )(unlift((l: Leafs) => Some((l.name, l.origin))))

  implicit val beansRead: Reads[Beans] = (
    (JsPath \ "beanName").read[String] and
    (JsPath \ "origin").read[String]
  )(Beans)

  implicit val coffeeWrites: Writes[Coffee] = (
    (JsPath \ "beans").write[Beans] and
    (JsPath \ "brewingTime").write[Int] and
    (JsPath \ "brewingTemperature").write[Int]
  )(unlift(Coffee.unapply))

  implicit val coffeeReads: Reads[Coffee] = (
    (JsPath \ "beans").read[Beans] and
    (JsPath \ "brewingTime").read[Int] and
    (JsPath \ "brewingTemperature").read[Int]
  )(Coffee)

  implicit val beansWritesWithMixin: Writes[Beans] = (
    (JsPath \ "beanName").write[String] and
      originWrites
    )(unlift(Beans.unapply))

  implicit val leafsWritesWithMixin: Writes[Leafs] = (
    (JsPath \ "leafName").write[String] and
      originWrites
    )(unlift(Leafs.unapply))

  lazy val originWrites =

  // TODO make json encoding and decoing examples
}
