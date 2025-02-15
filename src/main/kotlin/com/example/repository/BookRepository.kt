package com.example.repository

import com.example.domain.Book
import com.example.domain.BookSearch

interface BookRepository {

    fun getById(id: Pair<String, Int>): Book?
    fun getByIsbn(isbn: String): List<Book>
    fun searchBook(book: BookSearch): List<Book>
    fun addBook(book: Book): Book
    fun checkoutBook(id: Pair<String, Int>): Book?

}
