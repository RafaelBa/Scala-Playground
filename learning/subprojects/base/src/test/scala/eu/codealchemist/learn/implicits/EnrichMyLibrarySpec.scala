package eu.codealchemist.learn.implicits

import org.specs2.Specification
import eu.codealchemist.learn.implicits.BookConvervter._

class EnrichMyLibrarySpec extends Specification { def is =
  "Enricht my Library pattern".title ^ s2"""
An e-book ist converted to a book by appending .asBook    $e1
"""

  def e1 = {
    val name = "name"
    val book = Book(name)
    val ebook = EBook(name)
    convertEBookToBook(ebook) must beEqualTo (book)
  }
}
