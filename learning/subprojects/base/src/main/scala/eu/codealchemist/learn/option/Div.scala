package eu.codealchemist.learn.option

object Div {
  /**
   * Dividing by 0 is not allowed. If the divisor is 0 you will get a None, otherwise a Some with the quotient in int.
   */
  def apply(x: Int, y: Int): Option[Int] =
    if (y == 0) None
    else Some(x / y)

  /**
   * You can map on a Option and you will get a new Option.
   * The function will be applied to the value in the Some and return a new Some with the result.
   * If you got a None here, it will simply return a None.
   */
  def doubling(maybeNumber: Option[Int]): Option[Int] = maybeNumber map (_ * 2)

  /**
   * You can use fold on an Option, were you have to specify what will be done in case of a None and
   * what will be done in case of a Some(value).
   * If you only want to get the value or a default value you would use 'getOrElse()'.
   */
  def getNumber(maybeNumber: Option[Int]): Int = maybeNumber.fold(0)((n) => n)
}