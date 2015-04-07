package net.codealchemist.learn

package object json {
  case class Expected[A](obj: A, json: String)

  def arabicaEthiopia = Expected[Coffee](
    obj = Coffee(Beans("Arabica", "Ethiopia"), 5, 100),
    json = """{"beans":{"beanName":"Arabica","origin":"Ethiopia"},"brewingTime":5,"brewingTemperature":100}"""
  )
}
