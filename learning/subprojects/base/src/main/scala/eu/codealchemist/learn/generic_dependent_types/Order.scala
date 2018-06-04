package eu.codealchemist.learn.generic_dependent_types

/**
  * We here simply say that this order has some kind of specific type of 'Vertical'.
  * The fields inside this case class are affected by this, as 'verticalData' is of whatever type is defined
  * inside of the parameter type we pass.
  * Even more, we pass the parameter also to 'Rate', where rate pics the types it needs for its fields,
  * depending on the type we passed in the type parameter.
  */
case class Order[V <: Vertical](
  userId: Long,
  rate: Rate[V],
  verticalData: V#OrderData
) {
  def transform[V2 <: Vertical](implicit transformer: Transformer[Order[V], Order[V2]]): Order[V2] = {
    transformer(this)
  }
}
