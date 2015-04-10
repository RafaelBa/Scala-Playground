package net.codealchemist.learn.json

case class Cocktail(name: String, ingredients: List[Ingredient], ice: Option[Ice])
case class Ingredient(name: String)

trait Ice {
  def amount: Int
}
case class Crushed(amount: Int) extends Ice
case class Cubed(amount: Int) extends Ice

case class CocktailTaste(name: String, taste: Taste)
case class Taste(sortOf: String, basic: String)
