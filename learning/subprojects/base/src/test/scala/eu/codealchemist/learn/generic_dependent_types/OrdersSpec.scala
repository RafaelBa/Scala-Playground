package eu.codealchemist.learn.generic_dependent_types

import org.specs2.Specification
import PriceCalculatorResolver._
import Transformer._

class OrdersSpec extends Specification { def is = 
  "Generic Dependent Types".title ^ s2"""
The goal is here to glue together some types that can never be mixed. But the original top class is generic.
So imagine you work with orders of contracts for different verticals. Here it is telecommunication and energy providers.
The orders have similar data, which user ordered, where do we send the invoice to and the contract that they just ordered.
Some of the data is different though. Energy needs a meter number and the information if the customer changes his supplier or relocated.
Telco on the other hand wants to know whether the customer wants to keep their old phone number and if so, then it needs to be provided.
Then, going further down you have the rate / contract, which has different conditions.
Energy would consider things like daytime and nighttime tariffs. Where telco would consider if a sms flatrate is included.

The two parts here show two different things:
  Resolving the correct methods / classes for the correct parameter class implicitly, so we don't need a runtime check
    Get yearly consumption for
      energy                         $yearlyConsumptionEnergy
      telco                          $yearlyConsumptionTelco

  Transforming the parametrized objects to a more general one for methods that don't need the specific cata
    Get an user by order
      that exists for
        energy                       $existingEnergyUser
        telco                        $existingTelcoUser
      that doesn't exist for
        energy                       $nonExistingEnergyUser
        telco                        $nonExistingTelcoUser
"""

  def yearlyConsumptionEnergy = {
    PriceCalculator.firstYear(energyOrder) should beEqualTo(90d)
  }

  def yearlyConsumptionTelco = {
    PriceCalculator.firstYear(telcoOrder) should beEqualTo(170d)
  }

  def existingEnergyUser = {
    UserDAO.getUserNameByOrder(
      energyOrder.copy(userId = 1L).transform[AnyVertical]
    ) should beSome("Herbert")
  }

  def existingTelcoUser = {
    UserDAO.getUserNameByOrder(
      telcoOrder.copy(userId = 2L).transform[AnyVertical]
    ) should beSome("Jose")
  }

  def nonExistingEnergyUser = {
    UserDAO.getUserNameByOrder(
      energyOrder.transform[AnyVertical]
    ) should beEmpty
  }

  def nonExistingTelcoUser = {
    UserDAO.getUserNameByOrder(
      telcoOrder.transform[AnyVertical]
    ) should beEmpty
  }

  val energyOrder = Order[Energy](
    0L,
    Rate[Energy](
      "telco rate",
      energy.RateData(
        "10115",
        5d,
        0.0005
      )
    ),
    energy.OrderData(
      "MN123",
      5000
    ))

  val telcoOrder = Order[Telco](
    0L,
    Rate[Telco](
      "telco rate",
      telco.RateData(
        10d,
        50d
      )
    ),
    telco.OrderData(
      "Customer 123",
      true
    ))
}
