package eu.codealchemist.learn.selftypes

/**
 * This trait shows dependency injection in form of the cake pattern.
 * You have a trait which needs specific components to work properly not demanding a specification.
 * So you know here, that you need a GrinderComponent, a FoamerComponent and a WaterHeaterComponent.
 */
trait EspressoMachine { self: GrinderComponent with FoamerComponent with WaterHeaterComponent =>
  /**
   * You do not need to know anything about the implementations, as far as you know, that every component can do its work.
   * You have a grinder that grinds, a water heater that heats and a foamer that foams and you just use them,
   * and it doesn't matter now which implementation you will get.
   */
  def makeCoffee: Coffee = new Coffee(grinder.grind, waterHeater.heat, foamer.foam)
}

/**
 * Giving here the EspressoMachine the implementations, which will just work as specified by the EspressoMachine trait,
 * but giving the 'flavour' of the mixed in implementations.
 */
object DeluxeEspressoMachine extends
    EspressoMachine with FineGrinderComponent with AutomaticFoamerComponent with FastWaterHeaterComponent

/**
 * Same thing, expect, that you need a little manual configuration as we decided here to make WaterHeater more
 * configurable.
 */
object BudgetEspressoMachine extends
    EspressoMachine with RoughGrinderComponent with ManualFoamerComponent with SlowWaterHeaterComponent {
  val waterHeater = new SlowWaterHeater(60)  // As we decided, that we want the SlowWaterHeater to be configurable
}                                            // we need to assign an Object at the creation of the EspressoMachine

/**
 * This one is even more configurable as you can give an parameter, that affects the temperature.
 */
class TimedEspressoMachine(timeInSeconds: Int) extends
    EspressoMachine with RoughGrinderComponent with SlowWaterHeaterComponent with AutomaticFoamerComponent {
  private val temperaturePerSecond = 2
  val waterHeater = new SlowWaterHeater(timeInSeconds * temperaturePerSecond)
  override val foamer = new AutomaticFoamer { override def foam = Foam(2) }  // A little hacky overriding
}

/**
 * Coffee class, nothing special here
 */
class Coffee(beans: GroundBeans, water: Water, foam: Foam) {
  override def toString =
    s"""Your Coffee:
      |Taste:       $taste
      |Temperature: $temperature
      |Foam:        $foamStiffness
    """.stripMargin

  def taste = (beans.grindFineness, water.temperature) match {
    case (FineGround,  temp) => Constants.Taste.intense
    case (RoughGround, temp) if temp > 80 => Constants.Taste.medium
    case (RoughGround, _) => Constants.Taste.low
  }

  def temperature = water.temperature match {
    case temp if temp > 80 => Constants.Temperature.hot
    case temp if temp > 60 => Constants.Temperature.warm
    case temp => Constants.Temperature.cold
  }

  def foamStiffness = foam.stiffness match {
    case stiff if stiff > 3 => Constants.Foam.rockHard
    case stiff if stiff > 0 => Constants.Foam.fluffy
    case stiff => Constants.Foam.noFoam
  }

}

object Constants {
  object Foam {
    val rockHard = "rock hard"
    val fluffy = "fluffy"
    val noFoam = "no foam"
  }

  object Taste {
    val intense = "intense"
    val medium = "medium"
    val low = "low"
  }

  object Temperature {
    val hot = "hot"
    val warm = "warm"
    val cold = "cold"
  }
}

