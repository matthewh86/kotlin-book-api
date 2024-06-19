package com.example.repository

import com.example.domain.Book

class BookRepositoryImpl : BookRepository {

    private val book:Book = Book(
    "1234567890123",
    "Over the Cliff",
    "Eileen Dover",
    )

    override fun getBookByIsbn(isbn: String) : Book {
        return book
    }

}
