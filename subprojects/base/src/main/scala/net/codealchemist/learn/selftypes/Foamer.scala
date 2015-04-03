package net.codealchemist.learn.selftypes

trait FoamerComponent {
  val foamer: Foamer

  trait Foamer {
    def foam: Foam
  }
}

trait AutomaticFoamerComponent extends FoamerComponent {
  val foamer = new AutomaticFoamer

  class AutomaticFoamer extends Foamer {
    def foam: Foam = Foam(4)
  }
}

trait ManualFoamerComponent extends FoamerComponent {
  val foamer = new ManualFoamer

  class ManualFoamer extends Foamer {
    def foam: Foam = Foam(0)
  }
}

case class Foam(stiffness: Int)