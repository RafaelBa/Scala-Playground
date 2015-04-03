package net.codealchemist.learn.selftypes

import org.specs2.Specification

class EspressoSpec extends Specification { def is =
  "Selftypes / Dependency Injection / Cake Pattern".title ^ s2"""
With the cake pattern you can solve one task in different ways
by mixing in different flavours of a kind.
Therefore there will be different values for different EspressoMachines due to the different flavours.

BudgetEspressoMachine
  Taste = low                  $e1
  Temperature = cold           $e2
  Foam = no foam               $e3

DeluxeEspressoMachine
  Taste = intense              $e4
  Temperature = hot            $e5
  Foam = rock hard             $e6

TimedEspressoMachine(${lowTime.toString})
  Taste = low                  $e7
  Temperature = warm           $e8
  Foam = fluffy                $e9

TimedEspressoMachine(${highTime.toString})
  Taste = medium               $e10
  Temperature = hot            $e11
  Foam = fluffy                $e12
"""

  def e1 = BudgetEspressoMachine.makeCoffee.taste         must beEqualTo(Constants.Taste.low)
  def e2 = BudgetEspressoMachine.makeCoffee.temperature   must beEqualTo(Constants.Temperature.cold)
  def e3 = BudgetEspressoMachine.makeCoffee.foamStiffness must beEqualTo(Constants.Foam.noFoam)

  def e4 = DeluxeEspressoMachine.makeCoffee.taste         must beEqualTo(Constants.Taste.intense)
  def e5 = DeluxeEspressoMachine.makeCoffee.temperature   must beEqualTo(Constants.Temperature.hot)
  def e6 = DeluxeEspressoMachine.makeCoffee.foamStiffness must beEqualTo(Constants.Foam.rockHard)

  val lowTime = 35
  val lowTimeMachine = new TimedEspressoMachine(lowTime)
  def e7 = lowTimeMachine.makeCoffee.taste                must beEqualTo(Constants.Taste.low)
  def e8 = lowTimeMachine.makeCoffee.temperature          must beEqualTo(Constants.Temperature.warm)
  def e9 = lowTimeMachine.makeCoffee.foamStiffness        must beEqualTo(Constants.Foam.fluffy)

  val highTime = 45
  val highTimeMachine = new TimedEspressoMachine(highTime)
  def e10 = highTimeMachine.makeCoffee.taste              must beEqualTo(Constants.Taste.medium)
  def e11 = highTimeMachine.makeCoffee.temperature        must beEqualTo(Constants.Temperature.hot)
  def e12 = highTimeMachine.makeCoffee.foamStiffness      must beEqualTo(Constants.Foam.fluffy)

}
