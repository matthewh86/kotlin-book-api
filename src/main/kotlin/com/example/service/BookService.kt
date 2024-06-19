package com.example.service

import com.example.domain.Book

interface BookService {

    fun getByIsbn(isbn: String) : Book?

}
