package com.example.service

import com.example.domain.Book
import com.example.domain.BookSearch

interface BookService {

    fun getByIsbn(isbn: String): List<Book>
    fun bookSearch(bookSearch: BookSearch): List<Book>
    fun addBook(book: Book): Book
    fun borrowBook(book: BookSearch): Book?
    fun booksBorrowed(): Map<String, Int>

}
