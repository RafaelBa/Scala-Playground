package net.codealchemist.learn.either

import org.specs2.Specification

class MoviesSpec extends Specification { def is =
  "Either".title ^ s2"""
Getting Either[NotFound, Movie]
  Right[Movie] if the given name exists                                         $e1
  Left[NotFound] if the given name doesn't exist                                $e2
Using map on a RightProjection of an Either[NotFound, Movie]
  Calling it with a Right[Movie] it returns a new Right with the modified value $e3
  Calling it with a Left[NotFound] returns the same Left                        $e4
Using for comprehension on one or more Eithers will yield
  a Right[String] with the applied function at the end of the for comprehension $e5
  a Left[NotFound]
    if one of the arguments is Left                                             $e6
    if both arguments are Left. The first accessed Left will be returned        $e7
Using fold on an Either will return the name for
  Left[NotFound]                                                                $e8
  Right[Movie]                                                                  $e9
"""

  val mib = "Men in Black"
  val mib2 = "Men in Black II"
  val evolution = "Evolution"
  val wa = "Waisting Away"
  val foundMenInBlack = Shelf(mib)
  val foundMenInBlack2 = Shelf(mib2)
  val notfoundEvolution = Shelf(evolution)
  val notfoundWastingAway = Shelf(wa)

  def e1 = foundMenInBlack should beRight(Movie(mib))
  def e2 = notfoundEvolution should beLeft(NotFound(Movie(evolution)))

  def e3 = Shelf.nameOfExistingMovie(foundMenInBlack) should beRight(mib)
  def e4 = Shelf.nameOfExistingMovie(notfoundEvolution) should beEqualTo(notfoundEvolution)

  def e5 = Shelf.sequel(foundMenInBlack, foundMenInBlack2) should beRight(s"$mib2 is the sequel of $mib")
  def e6 = Shelf.sequel(foundMenInBlack, notfoundEvolution) should beEqualTo(notfoundEvolution)
  def e7 = Shelf.sequel(notfoundWastingAway, notfoundEvolution) should beEqualTo(notfoundWastingAway)

  def e8 = Shelf.getName(foundMenInBlack) should beEqualTo(mib)
  def e9 = Shelf.getName(notfoundEvolution) should beEqualTo(evolution)
}
