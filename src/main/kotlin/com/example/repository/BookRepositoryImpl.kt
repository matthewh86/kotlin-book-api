package com.example.repository

import com.example.domain.Book
import com.example.domain.BookSearch
import java.util.Objects.isNull

class BookRepositoryImpl : BookRepository {

    private val books: List<Book> = listOf(
        Book(
            "1234567890123",
            "Over the Cliff",
            "Eileen Dover",
        )
    )

    private fun isbnFilter(bookSearch: BookSearch) =
        { book: Book -> isNull(bookSearch.isbn) || book.isbn.equals(bookSearch.isbn) }

    private fun titleFilter(bookSearch: BookSearch) =
        { book: Book -> isNull(bookSearch.title) || book.title.equals(bookSearch.title) }

    private fun authorFilter(bookSearch: BookSearch) =
        { book: Book -> isNull(bookSearch.author) || book.author.equals(bookSearch.author) }

    override fun getByIsbn(isbn: String): Book? {
        return books.filter(
            isbnFilter(
                BookSearch(
                    isbn = isbn
                )
            )
        ).firstOrNull()
    }

    override fun searchBook(bookSearch: BookSearch): List<Book> {
        return books.filter { candidate ->
            listOf(
                isbnFilter(bookSearch),
                titleFilter(bookSearch),
                authorFilter(bookSearch),
            ).all { it(candidate) }
        }
    }

}
