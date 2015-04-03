package net.codealchemist.learn

package object exceptions {
  trait Error
  case object EmptyArgumentError extends Error
  case object IllegalArgumentError extends Error
  case object RuntimeError extends Error
  case object UnspecifiedError extends Error
}
