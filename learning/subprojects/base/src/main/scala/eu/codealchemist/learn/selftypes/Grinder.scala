package eu.codealchemist.learn.selftypes

trait GrinderComponent {
  def grinder: Grinder

  trait Grinder {
    def grind: GroundBeans
  }
}

trait FineGrinderComponent extends GrinderComponent {
  val grinder = new FineGrinder

  class FineGrinder extends Grinder {
    def grind = GroundBeans(FineGround)
  }
}

trait RoughGrinderComponent extends GrinderComponent {
  val grinder = new RoughGrinder

  class RoughGrinder extends Grinder {
    def grind = GroundBeans(RoughGround)
  }
}

case class GroundBeans(grindFineness: GrindFineness)

trait GrindFineness
object FineGround extends GrindFineness
object RoughGround extends GrindFineness
