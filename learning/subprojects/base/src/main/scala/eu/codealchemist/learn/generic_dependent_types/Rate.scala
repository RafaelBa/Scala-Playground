package eu.codealchemist.learn.generic_dependent_types

case class Rate[V <: Vertical](
  rateName: String,
  verticalData: V#RateData
) {
  def transform[V2 <: Vertical](implicit transformer: Transformer[Rate[V], Rate[V2]]): Rate[V2] = {
    transformer(this)
  }
}
