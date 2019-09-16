package eu.codealchemist.playground

import simulacrum._

object TCs {
  import Types.Vector

  @typeclass trait Show[A] {
    def show(self: A): String
  }

  @typeclass trait ScalarMultiplication[A] {
    @op("✕") def multiply(self: A, other: Long): A
  }

  @typeclass trait VectorCalculation[A] {
    @op("↦") def addition(self: A, other: A): Vector
  }
}

object Types {
  import TCs._

  case class Point(x: Long, y: Long)
  case class Vector(x: Long, y: Long)

  implicit val pointShow: Show[Point] = (self: Point) =>
    s"‧(${self.x}, ${self.y})"
  implicit val vectorShow: Show[Vector] = (self: Vector) =>
    s"→(${self.x}, ${self.y})"

  implicit val vectorScalarMultiplication: ScalarMultiplication[Vector] =
    (self: Vector, other: Long) => Vector(self.x * other, self.y * other)

  implicit val pointVectorCalculation: VectorCalculation[Point] =
    (self: Point, other: Point) => Vector(other.x - self.x, other.y - self.y)

  implicit val vectorVectorCalculation: VectorCalculation[Vector] =
    (self: Vector, other: Vector) => Vector(self.x + other.x, self.y + other.y)
}

object Main extends App {
  import TCs._
  import ScalarMultiplication.ops._
  import VectorCalculation.ops._
  import Show.ops._
  import Types._

  val p1 = Point(1, 2)
  val p2 = Point(3, 3)
  val v1 = p1 ↦ p2
  val v2 = v1 ✕ 3L
  val v3 = v1 ↦ v2

  println(s"p1: ${p1.show}, p2: ${p2.show}")
  println(s"v1: ${v1.show}")
  println(s"v2: ${v2.show}")
  println(s"v3: ${v3.show}")

  private def f(p: Point, other: Long)(
      implicit ev: ScalarMultiplication[Point]
  ): Point =
    p ✕ other
}
