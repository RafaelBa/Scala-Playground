package eu.codealchemist.learn.generics.generic_dependet_types

case class Rate[V <: Vertical](
  rateName: String,
  verticalData: V#RateData
) /*{
  def toVertical[V2 <: Vertical](
    implicit t: Transformation[ServiceProviderRate[V], Rate[V2]]
  ): ServiceProviderRate[OtherVertical] = t(this)
}   */
