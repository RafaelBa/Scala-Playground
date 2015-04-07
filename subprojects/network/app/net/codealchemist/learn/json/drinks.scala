package net.codealchemist.learn.json

trait HotDrink {
  def name: String
  def brewingTime: Int
  def brewingTemperature: Int
}

case class Coffee(beans: Beans, brewingTime: Int, brewingTemperature: Int) extends HotDrink {
  def name = s"${beans.name} (${beans.origin})"
}

case class Tea(leafs: Leafs, brewingTime: Int, brewingTemperature: Int) extends HotDrink {
  def name = s"${leafs.name} (${leafs.origin})"
}

case class Beans(name: String, origin: String)
case class Leafs(name: String, origin: String)