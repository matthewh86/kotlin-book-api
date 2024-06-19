package com.example.service

import com.example.domain.Book
import com.example.domain.BookSearch

interface BookService {

    fun getByIsbn(isbn: String): Book?
    fun bookSearch(bookSearch: BookSearch): List<Book>

}
