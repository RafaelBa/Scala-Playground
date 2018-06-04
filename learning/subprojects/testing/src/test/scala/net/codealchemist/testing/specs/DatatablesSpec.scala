package eu.codealchemist.testing.specs

import org.specs2.Specification
import org.specs2.specification.Tables

class DatatablesSpec extends Specification with Tables { def is = "Datatables".title ^ s2"""
Datatables are a quiet easy way to test your algorithms against multiple data.

  Adding to Ints should yield the sum of those numbers    $dataTable
  The examples can also be executed concurrently          $concurrent
  Beware that Strings need a slightly other operator      $strings
"""

  def dataTable =
    "x" | "y" | "sum" |>
     1  !  2  !   3   |
     1  !  1  !   2   |
     4  !  5  !   9   |
     5  ! -2  !   3   | {(x, y, sum) => x + y should beEqualTo(sum)}

  def concurrent =
    "x" | "y" | "sum" |>
     1  !  2  !   3   |
     5  ! -2  !   3   |* {(x, y, sum) => x + y should beEqualTo(sum)}

  def strings =
    "s"   || "c" |>
    "hi"  !! "h" |
    "you" !! "y" | {(s, c) => s should startingWith(c)}
}
