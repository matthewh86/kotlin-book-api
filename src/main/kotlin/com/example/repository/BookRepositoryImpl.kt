package com.example.repository

import com.example.domain.Book
import com.example.domain.BookSearch
import java.util.Objects.isNull

class BookRepositoryImpl : BookRepository {

    //TODO this is probably very inefficient
    private var books: MutableList<Book> = ArrayList(
        listOf(
            Book(
                Pair("1234567890123", 1),
                "1234567890123",
                "Over the Cliff",
                "Eileen Dover",
                false,
                false,
            )
        )
    )

    private fun idFilter(bookSearch: BookSearch) =
        { book: Book -> isNull(bookSearch.id) || book.id?.equals(bookSearch.id) ?: true }

    private fun isbnFilter(bookSearch: BookSearch) =
        { book: Book -> isNull(bookSearch.isbn) || book.isbn.equals(bookSearch.isbn) }

    private fun titleFilter(bookSearch: BookSearch) =
        { book: Book -> isNull(bookSearch.title) || book.title.equals(bookSearch.title) }

    private fun authorFilter(bookSearch: BookSearch) =
        { book: Book -> isNull(bookSearch.author) || book.author.equals(bookSearch.author) }

    private fun referenceFilter(bookSearch: BookSearch) =
        { book: Book -> isNull(bookSearch.reference) || book.reference.equals(bookSearch.reference) }

    private fun checkedOutFilter(bookSearch: BookSearch) =
        { book: Book -> isNull(bookSearch.checkedOut) || book.checkedOut.equals(bookSearch.checkedOut) }

    override fun getById(id: Pair<String, Int>): Book? {
        return books.filter(
            idFilter(
                BookSearch(
                    id = id
                )
            )
        ).firstOrNull()
    }

    override fun getByIsbn(isbn: String): List<Book> {
        return books.filter(
            isbnFilter(
                BookSearch(
                    isbn = isbn
                )
            )
        )
    }

    override fun searchBook(bookSearch: BookSearch): List<Book> {
        return books.filter { candidate ->
            listOf(
                idFilter(bookSearch),
                isbnFilter(bookSearch),
                titleFilter(bookSearch),
                authorFilter(bookSearch),
                referenceFilter(bookSearch),
                checkedOutFilter(bookSearch),
            ).all { it(candidate) }
        }
    }

    override fun addBook(book: Book): Book {
        val newBook = book.copy()
        val results = searchBook(
            BookSearch(
                isbn = book.isbn,
                title = book.title,
                author = book.author,
                reference = book.reference,
            )
        )

        newBook.id = Pair(book.isbn, results.size + 1)
        books.add(newBook)
        return newBook;
    }

    override fun checkoutBook(id: Pair<String, Int>): Book? {
        val availableBook = getById(id)
        if (availableBook != null) {
            availableBook.checkedOut = true
        }
        return availableBook;
    }

}
