package com.example.repository

import com.example.domain.Book

interface BookRepository {

    fun getByIsbn(isbn: String) : Book?

}
