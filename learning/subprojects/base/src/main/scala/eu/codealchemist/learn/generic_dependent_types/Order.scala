package eu.codealchemist.learn.generics.generic_dependet_types

case class Order[V <: Vertical](
  userId: Long,
  recommendation: Rate[V],
  verticalData: V#OrderData
) /*{
  def toVertical[OtherVertical <: OrderVertical](
    implicit t: Transformation[Order[Vertical], Order[OtherVertical]]
  ): Order[OtherVertical] = t(this)

  def toJson(implicit formats: OrderFormat[Vertical]): JsValue = Json.toJson(this)(formats.orderFormat)
}

object Order
{
  def transformVertical[T <: Vertical, U <: Vertical](
    order: Order[T]
  )(implicit
    orderDataTransformation: Transformation[T#OrderData, U#OrderData],
    rateTransformation: Transformation[Rate[T], Rate[U]]
  ): Order[U] = {
    val fromGen = LabelledGeneric[Order[T]]
    val toGen = LabelledGeneric[Order[U]]

    val fromFields = fromGen.to(order)

    val up = fromFields
      .updateWith('custom_data)(customDataTransformation(_))
      .updateWith('service_provider_to_service_category_mapping)(providerCategoryMappingTransformation(_))
      .updateWith('rate)(rateTransformation(_))

    toGen.from(up)
  }
}
*/
