package eu.codealchemist.testing.scalacheck

import org.scalacheck.{Arbitrary, Gen}
import org.specs2.{ScalaCheck, Specification}

class ScalaCheckSpec extends Specification with ScalaCheck { def is =
"ScalaCheck".title ^ s2"""
ScalaCheck is used for testing properties with arbitrary generated test data running every test multiple times with different values.

ScalaCheck provides implicit generators for basic types, which works fine with
  single parameter                                       $single
  multiple parameters                                    $multi

You also can write your own generators for
  simple classes                                         $simple
  classes depending on other classes' generators         $complex
  classes needing a List                                 $withList

ScalaCheck gives you the possibility to create values with restrictions for all types like
  ints                                                   $ints
  custom classes                                         $custom


"""

  def single = prop((i: Int) => i.getClass should beEqualTo(classOf[Int]))
  def multi = prop((i: Int, c: Char) => c + i should not(beEqualTo(c.toString + i.toString)))

  def simple = prop((x: X, y: Y) => Coordinate(X(x.v), Y(y.v)) should beEqualTo(Coordinate(x, y)))
  def complex = prop((c: Coordinate) => c.r should greaterThanOrEqualTo(0D))
  def withList = prop((cs: Coordinates) => cs.toString should contain("|"))

  def ints = prop((i: Int) => (i - 2) should be greaterThan(0)).setGen(greaterTwoGen)
  def custom = prop((c: Coordinate) => c should beInFirstQuadrant).setGen(firstQuadrant)

  def beInFirstQuadrant = havePositiveAbscissa and havePositiveOrdinate
  def havePositiveAbscissa = beGreaterThanOrEqualTo(0) ^^ ((_: Coordinate).abscissa.v)
  def havePositiveOrdinate = beGreaterThanOrEqualTo(0) ^^ ((_: Coordinate).ordinate.v)


  case class StringMultiplier(s: String, i: Int) {
    override def toString: String = toString(i)
    private def toString(n: Int): String =
      if (n == 0) ""
      else s + toString(n - 1)
  }

  case class X(v: Int)
  case class Y(v: Int)

  case class Coordinate(abscissa: X, ordinate: Y) {
    def r: Double = Math.sqrt(abscissa.v * abscissa.v + ordinate.v * ordinate.v)
    override def toString = s"($abscissa | $ordinate)"
  }

  case class Coordinates(cs: List[Coordinate]) {
    override def toString =
      if (cs.isEmpty) ""
      else cs.tail.foldLeft(cs.head.toString)((s: String, c: Coordinate) => s"$s -> ${c.toString}")
  }

  lazy val xGen: Gen[X] = Arbitrary.arbInt.arbitrary.map(X(_))
  implicit lazy val xArb: Arbitrary[X] = Arbitrary(xGen)

  lazy val yGen: Gen[Y] = Arbitrary.arbInt.arbitrary.map(Y(_))
  implicit lazy val yArb: Arbitrary[Y] = Arbitrary(yGen)

  implicit lazy val coordinateGen = for {
    x <- xGen
    y <- yGen
  } yield Coordinate(x, y)

  implicit lazy val coordinateArb: Arbitrary[Coordinate] = Arbitrary(coordinateGen)

  lazy val coordinateListGen: Gen[Coordinates] = for {
    cs <- Arbitrary.arbitrary[List[Coordinate]] suchThat (_.nonEmpty)
  } yield Coordinates(cs)

  implicit lazy val coordinateListArb: Arbitrary[Coordinates] = Arbitrary(coordinateListGen)

  lazy val greaterTwoGen = for {
    n <- Arbitrary.arbInt.arbitrary if (n > 2)
  } yield n

  lazy val firstQuadrant = for {
    i <- Arbitrary.arbInt.arbitrary if (i > 0)
    j <- Arbitrary.arbInt.arbitrary if (j > 0)
  } yield Coordinate(X(i), Y(j))
}
