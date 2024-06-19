package com.example.service

import com.example.domain.Book
import com.example.domain.BookSearch
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class BookServiceImplTest {

    private val underTest = BookServiceImpl()
    private lateinit var expected: Book

    @BeforeTest
    fun beforeTest() {
        expected = Book(
            id = Pair("1234567890123", 1),
            isbn = "1234567890123",
            title = "Over the Cliff",
            author = "Eileen Dover",
            reference = false,
            checkedOut = false,
        )
    }

    @Test
    fun checkoutBook_checksOutAvailableBook() {
        val bookSearch = BookSearch(
            isbn = "1234567890123",
        )

        expected.checkedOut = true
        assertEquals(expected, underTest.borrowBook(bookSearch))
        assertEquals(expected, underTest.getByIsbn("1234567890123").firstOrNull())
    }

    @Test
    fun checkoutBook_returnsEmpty_ifNoAvailableBook() {
        val bookSearch = BookSearch(
            isbn = "1234567890123",
        )

        underTest.borrowBook(bookSearch)
        assertNull(underTest.borrowBook(bookSearch))
        assertEquals(1, underTest.getByIsbn("1234567890123").size)
    }

    @Test
    fun checkoutBook_returnsEmpty_ifBookIsReference() {
        val bookSearch = BookSearch(
            isbn = "0000000000001",
        )
        val book = Book(
            isbn = "0000000000001",
            title = "Hutt Economics",
            author = "Jabba the Hutt",
            reference = true,
        )
        underTest.addBook(book)

        underTest.borrowBook(bookSearch)
        assertNull(underTest.borrowBook(bookSearch))
        assertEquals(1, underTest.getByIsbn("0000000000001").size)
    }

}
