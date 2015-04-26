package net.codealchemist.testing.specs

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
    an Exception of the right type is thrown                      $e11
    a Try returns a
     Success with a specific value                                $e12
     Failure with a specific value                                $e13

  Map Matchers let you test for
    Keys                                                          $e14
    Values                                                        $e15
    Pairs                                                         $e16

  Some Matchers can be used on every Object, just like the beEqualTo, for example the
    beLike                       $e17
    beOneOf                      $e18
    beNull                       $e19

  Don't forget, that you can use Matchers inside other Matchers most of the time like in the
    beSome                       $e20
    beRight                      $e21
    haveKeys                     $e22
    haveValues                   $e23
    exactly                      $e24
    not                          $e25

  Special Matchers for Lists can process them by looking if the List
    has a specific size                                           ${L.size}
    contains some elements                                        ${L.allOf}
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

  def e11 = failure
  def e12 = failure
  def e13 = failure
  def e14 = failure
  def e15 = failure
  def e16 = failure
  def e17 = failure
  def e18 = failure
  def e19 = failure
  def e20 = failure
  def e21 = failure
  def e22 = failure
  def e23 = failure
  def e24 = failure
  def e25 = failure

  object L {
    lazy val l = List(2, 3, 4)
    
    def size = l should have size(3)
    def allOf = l should contain(4, 3)
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


