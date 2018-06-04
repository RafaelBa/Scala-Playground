package net.codealchemist.learn.option

import org.specs2.Specification

class DivSpec extends Specification { def is =
  "Options".title ^ s2"""
By hitting the specifications you can get from an function with an Option
  a None                                                                     $e1
  a Some with a value in it                                                  $e2

You can map on a Option which will yield
  a None if it was a None before                                             $e3
  a Some with the result value                                               $e4

You can fold on a Option which will yield the result specified for
  a None                                                                     $e5
  a Some with its value                                                      $e6
"""

  def e1 = Div(3, 0) must beNone
  def e2 = Div(4, 2) must beSome(2)

  def e3 = Div.doubling(None) must beNone
  def e4 = Div.doubling(Some(3)) must beSome(6)

  def e5 = Div.getNumber(None) must beEqualTo(0)
  def e6 = Div.getNumber(Some(12)) must beEqualTo(12)

}
