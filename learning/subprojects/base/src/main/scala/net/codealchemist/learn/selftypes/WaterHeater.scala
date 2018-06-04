package net.codealchemist.learn.selftypes

trait WaterHeaterComponent {
  val waterHeater: WaterHeater

  trait WaterHeater {
    def heat: Water
  }
}

trait SlowWaterHeaterComponent extends WaterHeaterComponent {
  val waterHeater: WaterHeater    // We are leaving this one open for later configurations.
                                  // So this is the more configurable but less comfortable way.
  class SlowWaterHeater(temperature: Int) extends WaterHeater {
    def heat = Water(temperature)
  }
}

trait FastWaterHeaterComponent extends WaterHeaterComponent {
  val waterHeater = new FastWaterHeater

  class FastWaterHeater extends WaterHeater {
    def heat = Water(85)
  }
}


case class Water(temperature: Int)