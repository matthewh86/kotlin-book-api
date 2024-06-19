package com.example.service

import com.example.domain.Book
import com.example.repository.BookRepositoryImpl

class BookServiceImpl : BookService {

    // TODO: use koin for DI
    private val bookRepository = BookRepositoryImpl();

    override fun getByIsbn(isbn: String): Book? {
        return bookRepository.getByIsbn(isbn)
    }

}
