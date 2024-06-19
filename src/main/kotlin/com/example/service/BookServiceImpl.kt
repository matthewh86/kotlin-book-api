package com.example.service

import com.example.domain.Book
import com.example.domain.BookSearch
import com.example.repository.BookRepositoryImpl

class BookServiceImpl : BookService {

    // TODO: use koin for DI
    private val bookRepository = BookRepositoryImpl();

    override fun getByIsbn(isbn: String): Book? {
        return bookRepository.getByIsbn(isbn)
    }

    override fun bookSearch(bookSearch: BookSearch): List<Book> {
        return bookRepository.searchBook(bookSearch)
    }

    override fun addBook(book: Book): Book {
        return bookRepository.addBook(book);
    }

}
