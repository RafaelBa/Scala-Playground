package eu.codealchemist.learn.generic_dependent_types

trait Transformer[In, Out]
{
  def apply(in: In): Out
}

/**
  * This is how you define the transformations for a general object.
  * For the sake of an easy approach this is copy paste and kept very simple.
  * So everytime you go Energy -> this implicits are looked up, the correct one is picked up,
  * also picks up the one for Rate, as we transform Rate, too, and returns you the transformed Order.
  * If you go Any -> Energy the compiler will tell you that this doesn't work, as you have not defined a transformer.
  *
  * So this basically gives you a compile time check of allowed transistions.
  */
object Transformer {
  implicit val rateTransformerEnergyAny = new Transformer[Rate[Energy], Rate[AnyVertical]] {
    override def apply(in: Rate[Energy]): Rate[AnyVertical] = {
      Rate[AnyVertical](
        in.rateName,
        in.verticalData.asInstanceOf[Any]
      )
    }
  }

  implicit val rateTransformerTelcoAny = new Transformer[Rate[Telco], Rate[AnyVertical]] {
    override def apply(in: Rate[Telco]): Rate[AnyVertical] = {
      Rate[AnyVertical](
        in.rateName,
        in.verticalData.asInstanceOf[Any]
      )
    }
  }

  implicit val orderTransformerEnergyAny = new Transformer[Order[Energy], Order[AnyVertical]] {
    override def apply(in: Order[Energy]): Order[AnyVertical] = {
      Order[AnyVertical](
        in.userId,
        in.rate.transform[AnyVertical],
        in.verticalData.asInstanceOf[Any]
      )
    }
  }

  implicit val orderTransformerTelcoAny = new Transformer[Order[Telco], Order[AnyVertical]] {
    override def apply(in: Order[Telco]): Order[AnyVertical] = {
      Order[AnyVertical](
        in.userId,
        in.rate.transform[AnyVertical],
        in.verticalData.asInstanceOf[Any]
      )
    }
  }
}
