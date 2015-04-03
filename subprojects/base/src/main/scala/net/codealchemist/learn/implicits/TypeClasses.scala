package net.codealchemist.learn.implicits


/**
 * The type class is a trait providing methods for a certain kind of class collection.
 * It's kind of functional way of inheritance.
 */
trait AlternatingCurrent[T] {  // This is the type class
  def resistance(frequency: Double)(component: T): Double
}

/**
 * The implementations are simple in an implicit object implementing the trait for each of the class demanding this method.
 * This way you have a lose coupling between the type and the methods it can be applied to.
 */
object AlternatingCurrent {
  implicit object ResistorInAlternatingCurrent extends AlternatingCurrent[Resistor] {
    def resistance(frequency: Double)(component: Resistor): Double = component.value
  }

  implicit object CoilInAlternatingCurrent extends AlternatingCurrent[Coil] {
    def resistance(frequency: Double)(component: Coil): Double = 2 * frequency * math.Pi * component.inductance
  }

  implicit object CapacitorInAlternatingCurrent extends AlternatingCurrent[Capacitor] {
    def resistance(frequency: Double)(component: Capacitor): Double = -1 / (2 * frequency * math.Pi * component.capacitance)
  }
}


/**
 * The implementation for getting the value of the resistance depending on the
 * frequency is stored outside of each of the classes in a type class.
 * The classes just call the evidence (the type class's) method, where the type class will decide which method to choose
 * according to the type that is calling.
 */
case class Resistor(value: Int) {

  /**
   * This is a very simple example for the usage of type classes. The real power can be revealed when the calling
   * method is making some more complex things using the alternating current multiple times and if the alternating current
   * type class provides more methods.
   * TODO Something like 'ac.fun1(ac.fun2(this), ac.fun2(this))' would be a nice usage for a type class.
   */
  def resistanceAtFrequency(f: Double)(implicit ac: AlternatingCurrent[Resistor]) = ac.resistance(f)(this)
}

case class Coil(inductance: Double) {
  def resistanceAtFrequency(f: Double)(implicit ac: AlternatingCurrent[Coil]) = ac.resistance(f)(this)
}

case class Capacitor(capacitance: Double) {
  def resistanceAtFrequency(f: Double)(implicit ac: AlternatingCurrent[Capacitor]) = ac.resistance(f)(this)
}