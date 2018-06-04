package eu.codealchemist.learn.implicits

object Conversion {
  def greetingPerson(person: Person): String = s"Hello ${person.name}"

  def leavingParty(party: Party): String = s"Leaving ${party.toString}"

  case class Person(name: String)

  /**
   * When imported or in Scope the compiler will use this method if an conversion from String to Person is needed.
   */
  implicit def stringToPerson(name: String): Person = Person(name)

  /**
   * This is just the same as 'case class Party' and 'implicit def stringToParty(location: String): Party = new Party(location)'.
   */
  implicit class Party(location: String) {
    override def toString = s"party at $location"
  }
}

object PeopleAtParty {
  import Conversion.greetingPerson
  import Conversion.leavingParty

  /**
   * You need to import the methods which make the implicit conversions.
   */
  import Conversion.stringToPerson

  /**
   * Giving a String where a Person is required,
   * the compiler will look for implicit defs to convert the string directly into a Person
   * and putting it as argument into the greetingPerson method.
   */
  def greeting(name: String): String = greetingPerson(name)

  /**
   * This just works like the example above.
   */
  import Conversion.Party
  def leaving(from: String): String = leavingParty(from)

}
