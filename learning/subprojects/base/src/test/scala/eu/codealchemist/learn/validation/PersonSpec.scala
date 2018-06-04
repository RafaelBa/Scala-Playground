package eu.codealchemist.learn.validation

import org.specs2._
import scalaz._, Scalaz._

class PersonSpec extends Specification { def is =
  "Scalaz Validations".title ^ s2"""
Due to better maintain we will use ValidationNel.
In Fact ValidationNel[Error, Value] is the same as Validation[NonEmptyList[Error]], Value]

You can collect and combine the validations where a combination of
  no Failures yields the value of the called function                                            $e1
  one or more Failures yields the a list of these Failures                                       $e2

You can use map on a Validation where
  you get a Failure(value) if the Validation is a Failure(value)                                 $e3
  you get a Success(newValue) if the Validation is a Success(value)                              $e4

You can fold on a Validation where
  the function for the left side is applied for a Failure(value)                                 $e5
  the function for the right side is applied for a Success(value)                                $e6

You can convert an Option into a Validation where
  None yields Failure(givenFailure)                                                              $e7
  Some(value) yields Success(value)                                                              $e8

You can convert an Either into a Validation where
  Left(value) yields Failure(value)                                                              $e9
  Right(value) yields Success(value)                                                             $e10
"""

  def e1 = {
    val fn = "firstName"
    val ln = "lastName"
    val age = 25
    val expectedValidation = Person(fn, ln, age).successNel[Invalid]
    Person(fn.successNel[Invalid], ln.successNel[Invalid], age.successNel[Invalid]) must beEqualTo(expectedValidation)
  }

  def e2 = {
    val fn = Invalid("fn")
    val ln = Invalid("ln")
    val age = Invalid("age")
    val expected = NonEmptyList(fn, ln, age).failure[Person]
    Person(fn.failureNel[String], ln.failureNel[String], age.failureNel[Int]) must beEqualTo(expected)

  }
  def e3 = {
    val failPerson = Invalid("fail").failureNel[Person]
    Person.doubleAge(failPerson) must beEqualTo(failPerson)
  }

  def e4 = {
    val person = Person("fn", "ln", 25)
    Person.doubleAge(person.successNel[Invalid]) must beEqualTo(Person(person.firstName, person.lastName, person.age * 2).successNel[Invalid])
  }

  def e5 = {
    val failPerson = Invalid("fail").failureNel[Person]
    Person.getPerson(failPerson) must beEqualTo(Person("", "", 0))
  }
  def e6 = {
    val person = Person("fn", "ln", 25)
    Person.getPerson(person.successNel[Invalid]) must beEqualTo(person)
  }

  def e7 = {
    val expected = Invalid("There was no Person").failureNel[Person]
    Person.fromOption(None) must beEqualTo(expected)
  }

  def e8 = {
    val expectedPerson = Person("fn", "ln", 25)
    Person.fromOption(Some(expectedPerson)) must beEqualTo(expectedPerson.successNel[Invalid])
  }

  def e9 = {
    val expectedInvalid = Invalid("wrong person")
    val invalidEither: Either[Invalid, Person] = Left(expectedInvalid)
    Person.fromEither(invalidEither) must beEqualTo(expectedInvalid.failureNel[Person])
  }

  def e10 = {
    val expectedPerson = Person("fn", "ln", 25)
    val personEither: Either[Invalid, Person] = Right(expectedPerson)
    Person.fromEither(personEither) must beEqualTo(expectedPerson.successNel[Invalid])
  }
}