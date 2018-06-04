package net.codealchemist.learn.exceptions

import scala.util.control.NonFatal
import scala.util.{Failure, Success, Try}

object Trying {

  /**
   * The object's apply method will just return a Try object with the evaluation of the methods called in it.
   */
  def apply(f: () => Int): Try[Int] = Try(f())

  /**
   * You can simply match the exact type of the Try, just like an Option or Either.
   * The Success contains the computed value and the Failure contains an Exception.
   * You can match the Exception as well.
   */
  def matching(f: () => Int): Either[Error, Int] = Try(f()) match {
      case Success(value) => Right(value)
      case Failure(iae: IllegalArgumentException) => Left(IllegalArgumentError)
      case Failure(re: RuntimeException) => Left(RuntimeError)
      case Failure(NonFatal(e)) => Left(UnspecifiedError)
    }

  /**
   * You can simply convert the Try to a Option.
   * Failure(_) -> None
   * Success(value) -> Some(value)
   * Pretty nice, isn't it?
   */
  def option(f: () => Int): Option[Int] = Try(f()) toOption

  /**
   * Try objects are monads you can map (and flatMap) them. They just behave like Options.
   * Success(value) -> Success(f(value))
   * Failure(_) -> Failure(_)
   */
  def mapping(f: () => Int): Try[String] = Try(f()) map { _.toString }

  /**
   * getOrElse makes pretty much what it sounds like: either you get the value in the Success or you get the default value.
   */
  def getOrElse(f: () => Int, default: Int): Int = Try(f()) getOrElse default

  /**
   * Here you will get a new Try object distingushing if there has been an Exception or not.
   * The new Try will be a Success, either with the computed value or with the default value, if an error occurred.
   * Failure(_) => Success(default)
   * Success(value) => Success(value)
   */
  def orElse(f: () => Int, default: Int): Try[Int] = Try(f()) orElse Try(default)
}
