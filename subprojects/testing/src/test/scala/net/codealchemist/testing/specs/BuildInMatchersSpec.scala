package net.codealchemist.testing.specs

import scala.util.Try
import org.specs2.Specification

class BuildInMatchersSpec extends Specification { def is = "Specs2 Matchers".title ^ s2"""
Specs2 comes with some ready to use matchers, and this here shows how to actually use them.

  There are some checks for equality like the equality of the
    Object                       $equality
    Type                         $hasType
    Reference                    $reference

  Matchers can help you with your Options, checking if it is
    None                         $noneOpt
    Some                         $someOpt
    You can even check if it is Some and matches a desired value  $someVal

  There are Matchers that can handle Eithers, so you can check if your Either is
    Left                         $leftEi
    Right                        $rightEi
    It can even check if there is a matching value in your
      Left                       $leftVal
      Right                      $rightVal

  Matchers for Exceptions let you see if
    an Exception of the right type is thrown                      $excType
    an Exception with a message has been thrown                   $excMsg
    a Try returns a
     Success with a specific value                                $trySuccess
     Failure with a specific value                                $tryFail

  Map Matchers let you test for
    Keys                                                          $mapKeys
    Values                                                        $mapVals
    Pairs                                                         $mapPairs

  Special Matchers for Lists can process them by looking if the List
    has a specific size                                           ${L.size}
    contains some elements                                        ${L.allOf}
    contains some elements of a other List                        ${L.oneOf}
    contains exactly these elements, no more, no less             ${L.exact}
    contains these elements in given order                        ${L.order}
    does not contain these elements                               ${L.notContain}
    contains elements matching the given predicates for
      any element                                                 ${L.predicateAny}
      all elements                                                ${L.predicateAll}
    contains elements of
      given amount                                                ${L.containAmount}
      amount in given range                                       ${L.containRange}
    contains all these elements, but distinguishing each          ${L.distinct}

"""

  def equality = 42 should beEqualTo(42)
  def hasType = "Instance" should beAnInstanceOf[String]
  def reference = {
    val s = "String"
    val t = s
    t should beTheSameAs(s)
  }

  def noneOpt = Option(null) should beNone
  def someOpt = Option(12) should beSome
  def someVal = Option("String") should beSome("String")

  def leftEi = Left(42) should beLeft
  def rightEi = Right("fourty-two") should beRight
  def leftVal = Left(42) should beLeft(42)
  def rightVal = Right("fourty-two") should beRight("fourty-two")

  def excType = { throw new IllegalArgumentException; 1 } should throwA[IllegalArgumentException]
  def excMsg = { throw new IllegalArgumentException("OBJECTION!"); 1 } should throwA[IllegalArgumentException]("OBJECTION!")
  def trySuccess = Try("Success") should beSuccessfulTry.withValue("Success")
  def tryFail = Try(throw new IllegalArgumentException("OBJECTION!")) should beFailedTry.withThrowable[IllegalArgumentException]("OBJECTION!")

  lazy val m = Map(1 -> "one", 2 -> "two")
  def mapKeys = m should haveKeys(2, 1)
  def mapVals = m should haveValues("two", "one")
  def mapPairs = m should havePairs(1 -> "one")

  def e20 = failure
  def e21 = failure
  def e22 = failure
  def e23 = failure
  def notPred = 42 should not(beGreaterThan(1337))
  def oneOf = 42 should beOneOf(17, 42, 1337)

  object L {
    lazy val l = List(2, 3, 4)
    
    def size = l should have size(3)
    def allOf = l should contain(4, 3)
    def oneOf = l should contain(beOneOf(2, 3, 4, 5)).forall
    def exact = List(1, 1, 2, 3, 4) should contain(exactly(1, 1, 4, 3, 2))
    def order = l should contain(3, 4).inOrder
    def notContain = l should not(contain(1, 5))
    def predicateAny = l should contain(beGreaterThan(1), beGreaterThan(3))
    def predicateAll = l should contain(beGreaterThan(1)).forall
    def containAmount = l should contain(beLessThan(4)).exactly(2 times)
    def containRange = l should contain(beLessThan(3)).between(0 times, 2 times)
    def distinct = List(1, 1, 2, 3) should contain(1, 1, 2, 3).onDistinctValues
  }
}


