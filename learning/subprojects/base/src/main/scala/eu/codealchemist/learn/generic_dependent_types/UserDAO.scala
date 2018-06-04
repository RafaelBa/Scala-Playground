package eu.codealchemist.learn.generic_dependent_types

object UserDAO {
  private val users = Map(
    1L -> "Herbert",
    2L -> "Jose"
  )

  def getUserNameByOrder(order: Order[AnyVertical]): Option[String] = {
    users.get(order.userId)
  }
}
