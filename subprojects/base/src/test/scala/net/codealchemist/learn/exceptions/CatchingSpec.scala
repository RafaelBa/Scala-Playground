package net.codealchemist.learn.exceptions

import org.specs2.Specification

class CatchingSpec extends Specification { def is =
  "Catching".title ^ s2"""
There is a functional approach where the catch is in the focus.
    Just using the 'catching' function without any further logic it will
      rethrow the caught exception                                         $rethrow1
      return the computed value                                            $rethrow2
    Further logic after 'catching' can be to get an
      Option which will yield a
        None if an Exception has been thrown                               $option1
        Some with the value if no Exception has been thrown                $option2
      Either which will yield a
        Left with the Throwable if an Exception has been thrown            $either1
        Right with the computed value if no Exception has been thrown      $either2
    There can also be a custom apply method which will
      apply the method to the exception that has been thrown               ${Applying().ex1}
      take no effect if no exception was thrown and computet value of the called function is returned  ${Applying().ex2}
    The Catch class has also a finally which will be called if
      an exception has been thrown                                         ${Finally().ex1}
      no exception has been thrown                                         ${Finally().ex2}
"""

  val value = 42
  val exception = new IllegalArgumentException
  val throwing = () => throw exception
  val valueFun = () => value

  def rethrow1 = Catching.catchingRethrow(throwing) should throwA[IllegalArgumentException]
  def rethrow2 = Catching.catchingRethrow(valueFun) should beEqualTo(value)

  def option1 = Catching.catchingOption(throwing) should beNone
  def option2 = Catching.catchingOption(valueFun) should beSome(value)

  def either1 = Catching.catchingEither(throwing) should beLeft(exception)
  def either2 = Catching.catchingEither(valueFun) should beRight(value)

  case class Applying() {
    var thrown = false
    val otherValue = -1
    val setThrown = (t: Throwable) => {
      thrown = true
      otherValue
    }

    def ex1 = Catching.withApply(throwing, setThrown) should beEqualTo(otherValue) and(thrown should beTrue)
    def ex2 = Catching.withApply(valueFun, setThrown) should beEqualTo(value) and(thrown should beFalse)
  }

  case class Finally() {
    var i = 0
    val settingI = () => i = value

    def ex1 = Catching.withFinally(throwing, settingI) should throwA[IllegalArgumentException] and(i should beEqualTo(value))
    def ex2 = Catching.withFinally(valueFun, settingI) should beEqualTo(value) and(i should beEqualTo(value))
  }
}