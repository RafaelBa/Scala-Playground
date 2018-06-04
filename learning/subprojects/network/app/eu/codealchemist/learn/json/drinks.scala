package eu.codealchemist.learn.json

case class Cocktail(name: String, ingredients: List[Ingredient])
case class Ingredient(name: String)

trait Drink {
  def kind: String
}
case class Beer(name: String, kind: String) extends Drink
case class Wine(name: String, kind: String) extends Drink

case class Whisky(name: String, taste: Taste)
case class Taste(body: String, finish: String)
