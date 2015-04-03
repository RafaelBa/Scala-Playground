package net.codealchemist.learn.exceptions

import javax.naming.NamingException

import org.specs2.Specification

class TryCatchSpec extends Specification { def is =
  "Try and Catch".title ^ s2"""
The try-catch-block in scala is pretty similar to the java try-catch-mechnism.
If an Exception is thrown it can be caught and distinguished by type.
You can even build cascades of catching where you can catch
  the Exception by type and other criteria like message content   $e1
  the Exception only by type                                      $e2
  the Exceptions base Exception which of course catches
    the base type itself                                          $e3
    any other children of this type                               $e4
  every non fatal Exception                                       $e5
  A non fatal is an Exception that is not a ControlThrowable or Error or any other kind of system excption.
  It is strongyl discouraged to catch any other Exception as non fatal ones and block their way to the top.
"""

  def e1 = TryCatch(new IllegalArgumentException("There was no argument given")) should beEqualTo(EmptyArgumentError)
  def e2 = TryCatch(new IllegalArgumentException) should beEqualTo(IllegalArgumentError)
  def e3 = TryCatch(new RuntimeException) should beEqualTo(RuntimeError)
  def e4 = TryCatch(new NullPointerException) should beEqualTo(RuntimeError)
  def e5 = TryCatch(new NamingException) should beEqualTo(UnspecifiedError)
}
