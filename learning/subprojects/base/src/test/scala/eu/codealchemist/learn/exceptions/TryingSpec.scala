package eu.codealchemist.learn.exceptions

import org.specs2.Specification

class TryingSpec extends Specification { def is =
  "Try".title ^ s2"""
There is a functional way in scala to make a try-catch which is implemented in the Try class.

First of all 'Try.apply' return a Try object which will not let the exception out. So you will get a
  Failure if the exception has been thrown                                 $e1
  Success if the value was computed without exceptions                     $e2
As soon as you get a Try object you can do some nifty things with it because they're just monads. A nice thing to do is
  matching it to a
    Failure with a
      specific Exception                                                   $e3
      base type of an Exception                                            $e4
    Success                                                                $e5
  converting it to an Option where it yields a
    None if an Exception has been thrown                                   $e6
    Some with the value when no Exception occurred                         $e7
  map a function, so that you get
    still a Failure if an Exception ocurred                                $e8
    a Success with the modified value inside                               $e9
  applying a default value to 'getOrEles' which will return the
    default value if an Exception has been thrown                          $e10
    computed value if everything went alright                              $e11
  applying a default value to 'orElse' which will return a new Try with the
    default value if an Exception occurred                                 $e12
    computed value if no Exception has been thrown                         $e13
"""

  val exc = new IllegalArgumentException
  val throwing = () => throw exc
  val value = 42
  val valueFun = () => value
  val default = -1

  def e1 = Trying(throwing) should beFailedTry.withThrowable[IllegalArgumentException]
  def e2 = Trying(valueFun) should beSuccessfulTry.withValue(value)

  def e3 = Trying.matching(throwing) should beLeft(IllegalArgumentError)
  def e4 = Trying.matching(() => throw new NullPointerException) should beLeft(RuntimeError)
  def e5 = Trying.matching(valueFun) should beRight(value)

  def e6 = Trying.option(throwing) should beNone
  def e7 = Trying.option(valueFun) should beSome(value)

  def e8 = Trying.mapping(throwing) should beFailedTry.withThrowable[IllegalArgumentException]
  def e9 = Trying.mapping(valueFun) should beSuccessfulTry.withValue(value.toString)

  def e10 = Trying.getOrElse(throwing, default) should beEqualTo(default)
  def e11 = Trying.getOrElse(valueFun, default) should beEqualTo(value)

  def e12 = Trying.orElse(throwing, default) should beSuccessfulTry.withValue(default)
  def e13 = Trying.orElse(valueFun, default) should beSuccessfulTry.withValue(value)
}
