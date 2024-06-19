package com.example.service

import com.example.domain.Book
import com.example.domain.BookSearch
import com.example.repository.BookRepositoryImpl

class BookServiceImpl : BookService {

    // TODO: use koin for DI
    private val bookRepository = BookRepositoryImpl();

    override fun getByIsbn(isbn: String): List<Book> {
        return bookRepository.getByIsbn(isbn)
    }

    override fun bookSearch(bookSearch: BookSearch): List<Book> {
        return bookRepository.searchBook(bookSearch)
    }

    override fun addBook(book: Book): Book {
        if (book.id != null) {
            throw IllegalArgumentException("ID must not be specified")
        }
        return bookRepository.addBook(book);
    }

    override fun borrowBook(book: BookSearch): Book? {
        val availableBook = bookRepository.searchBook(book)
            .filter { book -> !book.checkedOut }
            .filter { book -> !book.reference }
            .firstOrNull()
        availableBook?.id?.let { bookRepository.checkoutBook(it) }
        return availableBook;
    }

}
