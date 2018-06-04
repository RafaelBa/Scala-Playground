package eu.codealchemist.learn.generic_dependent_types

object PriceCalculator
{
  /**
    * This is the generic function, that is called with a specific order, e.g. Order[Telco].
    * This means, that the compiler has to find implicitly a PriceCalculator[Telco].
    * The beauty here is that you can't call it with Order[AnyVertical], as this is not implemented.
    * You'll get an error during compile time and not during runtime.
    */
  def firstYear[T <: Vertical : PriceCalculator](order: Order[T]): Double = {
    implicitly[PriceCalculator[T]].firstYear(order)
  }
}

trait PriceCalculator[T <: Vertical] {
  def firstYear(order: Order[T]): Double
}

object PriceCalculatorResolver {
  implicit object EnergyPriceCalculator extends PriceCalculator[Energy] {
    override def firstYear(order: Order[Energy]): Double = {
      (
        order.verticalData.consumption * order.rate.verticalData.pricePerUnit +
        order.rate.verticalData.basePrice
        ) * 12
    }
  }

  implicit object TelcoPriceCalculator extends PriceCalculator[Telco] {
    override def firstYear(order: Order[Telco]): Double = {
      order.rate.verticalData.connectionFee + order.rate.verticalData.monthlyPrice * 12
    }
  }
}
