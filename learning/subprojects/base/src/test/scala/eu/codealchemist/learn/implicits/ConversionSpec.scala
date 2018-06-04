package eu.codealchemist.learn.implicits

import org.specs2.Specification

class ConversionSpec extends Specification { def is =
  "Implicit Type Conversion".title ^ s2"""
Using implicit def to convert a String to a Person implicitly      $e1
Using implicit class to convert a String to a Party implicitly     $e2
"""

  def e1 = PeopleAtParty.greeting("Aragon") must beEqualTo("Hello Aragon")
  def e2 = PeopleAtParty.leaving("Helm's Deep") must beEqualTo("Leaving party at Helm's Deep")
}
