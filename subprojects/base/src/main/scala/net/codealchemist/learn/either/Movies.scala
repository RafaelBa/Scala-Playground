package net.codealchemist.learn.either

case class Movie(name: String)

case class NotFound(movie: Movie)

object Shelf {

  val movies = List("Men in Black", "Men in Black II", "Back to the Future", "Indiana Jones")

  def apply(name: String) = {
    val movie = Movie(name)
    if (movies contains name) Right(movie) else Left(NotFound(movie))
  }

  /**
   * Using right on either and map on this RightProjection. But the Left's will not be touched at all.
   * Would work the exact same way with left and it's LeftProjection.
   * It will return Right(name) if e is a Right or Left(NotFound(Movie(name))) if it's a Left.
   */
  def nameOfExistingMovie(e: Either[NotFound, Movie]): Either[NotFound, String] = e.right.map { _.name }


  /**
   * Using here the for comprehension to access and combine two Rights.
   * This would work for Lefts as well.
   * Right(concatenated String of movie names) will be returned if there are two Rights else the first Left accessed in the for comprehension.
   */
  def sequel(first: Either[NotFound, Movie], second: Either[NotFound, Movie]): Either[NotFound, String] = for {
      firstMovie <- first.right        // Saying that you want to use a RightProjection, not a LeftProjection
      secondMovie <- second.right
      firstName <- Right(firstMovie.name).right    // Need to make a right and a RightProjection of the Right, for Right itself has no flatMap.
      secondName <- Right(secondMovie.name).right  // The flatMap is the access of the name value of the Movie object
    } yield s"$secondName is the sequel of $firstName"    // Making a desired operation and returning a Right[result.type] which would be here Right[String]

  /**
   * Using here a fold on an Either[NotFound, Movie]. The fold desires two functions and will use the correct function for Left and Right.
   * So you can kind of convert an Either into an other data type
   */
  def getName(e: Either[NotFound, Movie]): String = e.fold(_.movie.name, _.name)

}
