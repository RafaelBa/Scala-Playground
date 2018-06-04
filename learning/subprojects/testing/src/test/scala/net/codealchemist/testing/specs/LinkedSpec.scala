package net.codealchemist.testing.specs

import org.specs2.Specification
import subspec._

class LinkedSpec extends Specification { def is = "Linking Specifications".title ^ s2"""
Here you can see how linking Specifications looks like.

  ${ "This Spec is immediatly executed and linked" ~ new SubOne }
  ${ "This one is only linked" ~/ new SubTwo }
"""
}
