package net.codealchemist.learn.scalaz.validation

import scalaz._, Scalaz._
import Validation._

case class Invalid(message: String)

case class Person (firstName: String, lastName: String, age: Int)

object Person {

  /**
   * This is how Validation works. While ValidationNel is just better for handling a bunch of error messages
   * we will use this one. ValidationNel[X, Y] is the same as Validation[NonEmptyList[X], Y].
   * Validations are a lot like Eithers, but they are right-biased and you can concatenate all error messages,
   * as long as they are the same type.
   * You want to specify the return type (or the variable type) of the ApplicativBuilder result (x |@| y),
   */
  def apply(firstName: ValidationNel[Invalid, String], lastName: ValidationNel[Invalid, String], age: ValidationNel[Invalid, Int]): ValidationNel[Invalid, Person] =
    (firstName |@| lastName |@| age ) { (firstName: String, lastName: String, age: Int) =>
      new Person(firstName, lastName, age) }

  /**
   * Therefore Validations are right biased a map will only work on a success, just like an Option,
   * or will return the fail.
   */
  def doubleAge(vali: ValidationNel[Invalid, Person]): ValidationNel[Invalid, Person] =
    vali map { p: Person => Person(p.firstName, p.lastName, p.age * 2) }

  /**
   * You can fold Validations just like Eithers.
   */
  def getPerson(vali: ValidationNel[Invalid, Person]): Person =
    vali.fold(_ => Person("", "", 0), (p: Person) => p)

  /**
   * Converting an Option[Person] to a Validation by saying what to do, if it is a failure.
   * Need to wrap in a Nel here to get a ValidationNel.
   */
  def fromOption(maybePerson: Option[Person]): ValidationNel[Invalid, Person] =
    maybePerson.toSuccess(Invalid("There was no Person").wrapNel)

  /**
   * Converting a Scala Either to a scalaz Either, than to a Validation and last but not least to a ValidationNel.
   */
  def fromEither(eitherPerson: Either[Invalid, Person]): ValidationNel[Invalid, Person] =
    eitherPerson.disjunction.validation.toValidationNel
}
