package net.codealchemist.learn.json

import scala.concurrent.duration.Duration

trait HotDrink {
  def name: String
  def brewingTime: Duration
  def brewingTemperature: Int
}

case class Coffee(beans: Beans, brewingTime: Duration, brewingTemperature: Int) extends HotDrink {
  def name = s"${beans.name} (${beans.origin})"
}

case class Tea(leafs: Leafs, brewingTime: Duration, brewingTemperature: Int) extends HotDrink {
  def name = s"${leafs.name} (${leafs.origin})"
}

case class Beans(name: String, origin: String)
case class Leafs(name: String, origin: String)