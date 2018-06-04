package eu.codealchemist.learn.implicits

import org.specs2.Specification

class TypeClassesSpec extends Specification { def is =
  "Type Classes Pattern".title ^ s2"""
Resistor should return the right value for its resistance value       $e1
Capacitors should return the right value for its resistance value     $e2
Coils should return the right value for its resistance value          $e3
"""

  def e1 = {
    val rR = 12
    val r = Resistor(rR)
    r.resistanceAtFrequency(100) should beEqualTo(rR)
  }

  def e2 = {
    val f = 100
    val c = 0.05
    val xC = -1 / (2 * math.Pi * f * c)
    val cap = Capacitor(c)
    cap.resistanceAtFrequency(f) should beEqualTo(xC)
  }

  def e3 = {
    val f = 100
    val l = 50
    val xL = 2 * math.Pi * f * l
    val coil = Coil(l)
    coil.resistanceAtFrequency(f) should beEqualTo(xL)
  }

}
