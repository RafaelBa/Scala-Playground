package eu.codealchemist.testing.specs.subspec

import org.specs2.Specification

class SubOne extends Specification { def is = "SubSpec 1".title ^ s2"""
  Getting the size of a list should result in its length  $e
"""

  def e = List(1, 2, 3).size should beEqualTo(3)
}