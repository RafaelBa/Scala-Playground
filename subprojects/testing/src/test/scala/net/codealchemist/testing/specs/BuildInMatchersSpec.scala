package net.codealchemist.testing.specs

import org.specs2.Specification

class BuildInMatchersSpec extends Specification { def is = "Specs2 Matchers".title ^ s2"""
Specs2 comes with some ready to use matchers, and this here shows how to actually use them.

  There are some checks for equality like the equality of the
    Object                       $e1
    Type                         $e2
    Reference                    $e3

  Matchers can help you with your Options, checking if it is
    None                         $e4
    Some                         $e5
    You can even check if it is Some and matches a desired value  $e6

  There are Matchers that can handle Eithers, so you can check if your Either is
    Left                         $e7
    Right                        $e8
    It can even check if there is a matching value in your
      Left                       $e9
      Right                      $e10

  Special Matchers for Lists can process them by looking if the List
    contains some elements                                        $e11
    contains some of these elements, but not neccessaryly all     $e12
    contains exactly these elements, no more, no less             $e13
    contains these elements in given order                        $e14
    does not contain these elements                               $e15
    contains elements matching the given predicates               $e16
    contains elements of
      given amount                                                $e17
      amount in given range                                       $e18

  Matchers for Exceptions let you see if
    an Exception of the right type is thrown                      $e19
    a Try returns a
     Success with a specific value                                $e20
     Failure with a specific value                                $e21

  Map Matchers let you test for
    Keys                                                          $e22
    Values                                                        $e23
    Pairs                                                         $e24

  Some Matchers can be used on every Object, just like the beEqualTo, for example the
    beLike                       $e25
    beOneOf                      $e26
    beNull                       $e27
    haveClass                    $e28
    beAnInstanceOf               $e29

  Don't forget, that you can use Matchers inside other Matchers most of the time like in the
    beSome                       $e30
    beRight                      $e31
    haveKeys                     $e32
    haveValues                   $e33
    exactly                      $e34

"""

  def e1 = failure
  def e2 = failure
  def e3 = failure
  def e4 = failure
  def e5 = failure
  def e6 = failure
  def e7 = failure
  def e8 = failure
  def e9 = failure
  def e10 = failure
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
  def e26 = failure
  def e27 = failure
  def e28 = failure
  def e29 = failure
  def e30 = failure
  def e31 = failure
  def e32 = failure
  def e33 = failure
  def e34 = failure
}