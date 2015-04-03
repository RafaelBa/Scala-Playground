package net.codealchemist.learn.exceptions

import scala.util.control.{ ControlThrowable, NonFatal }

object TryCatch {
  /**
   * Showing, that the Scala try-catch is not much different from the Java try-catch.
   */
  def apply(thr: Throwable): Error = try {
      throw thr
    } catch {
      case iae: IllegalArgumentException if iae.getMessage != null && (iae.getMessage contains "no argument given") => EmptyArgumentError
      case iae: IllegalArgumentException => IllegalArgumentError
      case re: RuntimeException => RuntimeError
      // This is how you catch all non-fatal exceptions, i. e. all exceptions, that are caused by algorithms rather than by the JVM / system
      case NonFatal(e) => UnspecifiedError
      // Never catch an ControlThrowable, if you want to catch everything you should rethrow those
      case ct: ControlThrowable => throw ct
      // But you really never want to catch everything... never ever
      case t: Throwable => throw t
    }
}
