package net.codealchemist.learn.exceptions

import scala.util.control.Exception._

object Catching {
  /**
   * This will catch und just rethrow the exception.
   * 'catching' will return a Catch object. Then you have to apply the function you expect an Exception from.
   * You are actually calling the 'apply' method of your constructed Catch object.
   * Nethertheless you have an unconfigured Catch object where it will rethrow the Exception because of the lack of instructions
   * what to do with the sepcified exception.
   */
  def catchingRethrow(mayThrowOrNot: () => Int): Int =
    catching(classOf[IllegalArgumentException]) (mayThrowOrNot())

  /**
   * Catching the specified exceptions and return the result as Option[Int].
   */
  def catchingOption(mayThrowOrNot: () => Int): Option[Int] =
    catching(classOf[IllegalArgumentException]) opt mayThrowOrNot()

  /**
   * Catching the specified exceptions and return the result as Either[Throwable, Int].
   */
  def catchingEither(mayThrowOrNot: () => Int): Either[Throwable, Int] =
    catching(classOf[IllegalArgumentException]) either mayThrowOrNot()

  /**
   * If an Exception has been thrown then the given funtion in 'withApply' will be called.
   * This will handle the exception and the catching will return the value of the function given to 'withApply'.
   * If no Exception has been thrown the value of Catch's 'apply' block will be return.
   * This works a little bit like a 'fold' of an Option.
   */
  def withApply(mayThrowOrNot: () => Int, f: (Throwable) => Int) =
    catching(classOf[IllegalArgumentException]).withApply(f) (mayThrowOrNot())

  /**
   * The 'andFinally' takes a function with side effects and will call it once the exception handling is done.
   * Note that if you won't handle the thrown Exception (like not calling 'opt', 'either' or 'withApply') it will just
   * rethrow the exception. Nethertheless the finally part will be executed.
   */
  def withFinally(mayThrowOrNot: () => Int, f: () => Unit) = {
    catching(classOf[IllegalArgumentException]).andFinally(f()) (mayThrowOrNot())
  }
}
