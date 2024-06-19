package com.example.repository

import com.example.domain.Book

interface BookRepository {

    fun getBookByIsbn(isbn: String) : Book?

}
