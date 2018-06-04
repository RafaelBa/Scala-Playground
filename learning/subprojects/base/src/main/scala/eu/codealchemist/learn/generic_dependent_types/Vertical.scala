package eu.codealchemist.learn.generic_dependent_types

sealed trait Vertical {
  type OrderData
  type RateData
}

class Energy extends Vertical {
  type OrderData = energy.OrderData
  type RateData = energy.RateData
}

class Telco extends Vertical {
  type OrderData = telco.OrderData
  type RateData = telco.RateData
}

class AnyVertical extends Vertical {
  type OrderData = Any
  type RateData = Any
}
