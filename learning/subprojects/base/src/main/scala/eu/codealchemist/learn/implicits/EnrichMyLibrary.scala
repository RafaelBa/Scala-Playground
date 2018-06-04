package eu.codealchemist.learn.implicits

case class Book(title: String)

case class EBook(fileName: String)

object BookConvervter {
  implicit def ebookToBook(ebook: EBook): BookFromEBook = new BookFromEBook(ebook)

  /**
   * This class should never be called directly. It's only purpose is to provide the "implicit" method
   * for a special type, that isn't implicit at all.
   */
  class BookFromEBook(ebook: EBook) {
    def asBook: Book = Book(ebook.fileName)
  }

  /**
   * Here the asBook can be used directly on an EBook. The implicit conversion happens from EBook to BookFromEBook
   * so that you can use the asBook method on this object explicitly.
   */
  def convertEBookToBook(ebook: EBook): Book = ebook.asBook
}
