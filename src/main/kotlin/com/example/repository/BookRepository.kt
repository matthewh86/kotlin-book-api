package com.example.repository

import com.example.domain.Book
import com.example.domain.BookSearch

interface BookRepository {

    fun getByIsbn(isbn: String) : Book?
    fun searchBook(book: BookSearch) : List<Book>

}
