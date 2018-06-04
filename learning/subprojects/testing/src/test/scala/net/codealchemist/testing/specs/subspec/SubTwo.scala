package net.codealchemist.testing.specs.subspec

import org.specs2.Specification

class SubTwo extends Specification { def is = "SubSpec 2".title ^ s2"""
  Adding two numbers should result in their sum    $e
"""

  def e = 1 + 1 should beEqualTo(2)
}