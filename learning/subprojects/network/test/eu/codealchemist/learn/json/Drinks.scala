package eu.codealchemist.learn.json

object Drinks {
  lazy val caipi = Cocktail("Caipirinha", List(Ingredient("cane sugar"), Ingredient("cachaca"), Ingredient("lime")))
  lazy val caipiJson = """{"name":"Caipirinha","ingredients":[{"ingredient":"cane sugar"},{"ingredient":"cachaca"},{"ingredient":"lime"}]}"""
  lazy val erronousCocktailJson = """{"name":"Cuba Libre", "Taste":{"body":"full","finish":"straight"}}"""

  lazy val beer = Beer("Okocym", "strong")
  lazy val wine = Wine("Merlot", "dry")
  lazy val beerJson = """{"name":"Okocym","kindOfBeer":"strong"}"""
  lazy val wineJson = """{"name":"Merlot","kindOfWine":"dry"}"""
  lazy val erronousDrinkJson = """{"name":"Brugal","kindOfRum":"brown"}"""

  lazy val ardbeg = Whisky("Ardbeg", Taste("peaty", "smoky"))
  lazy val ardbegJson = """{"name":"Ardbeg","taste":{"body":"peaty","finish":"smoky"}}"""
  lazy val erronousWhiskyJson = """{"name":"Talisker","taste":"nice!"}"""
}
