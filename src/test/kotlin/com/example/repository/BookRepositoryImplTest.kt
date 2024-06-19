package com.example.repository

import com.example.domain.Book
import com.example.domain.BookSearch
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class BookRepositoryImplTest {

    private val underTest = BookRepositoryImpl()
    private val expected = Book(
        "1234567890123",
        "Over the Cliff",
        "Eileen Dover",
    )

    @Test
    fun getByIsbn_returnsRequested() {
        assertEquals(expected, underTest.getByIsbn("1234567890123"))
    }

    @Test
    fun getByIsbn_returnsNull_whenNotExists() {
        assertNull(underTest.getByIsbn("nobook"))
    }

    @Test
    fun searchBook_byIsbn_returnsExpected_whenExists() {
        val search = BookSearch(
            isbn = "1234567890123"
        )

        assertEquals(expected, underTest.searchBook(search).first())
    }

    @Test
    fun searchBook_byIsbn_returnsEmpty_whenNotExists() {
        val search = BookSearch(
            isbn = "0000000000000"
        )

        assertEquals(ArrayList(), underTest.searchBook(search))
    }

    @Test
    fun searchBook_byTitle_returnsExpected_whenExists() {
        val search = BookSearch(
            title = "Over the Cliff"
        )

        assertEquals(expected, underTest.searchBook(search).first())
    }

    @Test
    fun searchBook_byTitle_returnsEmpty_whenNotExists() {
        val search = BookSearch(
            title = "Foo"
        )

        assertEquals(ArrayList(), underTest.searchBook(search))
    }

    @Test
    fun searchBook_byAuthor_returnsExpected_whenExists() {
        val search = BookSearch(
            author = "Eileen Dover"
        )

        assertEquals(expected, underTest.searchBook(search).first())
    }

    @Test
    fun searchBook_byAuthor_returnsEmpty_whenNotExists() {
        val search = BookSearch(
            author = "Jabba the Hutt"
        )

        assertEquals(ArrayList(), underTest.searchBook(search))
    }

    @Test
    fun addBook_addsTheBook() {
        val book = Book(
            isbn = "0000000000001",
            title = "Hutt Economics",
            author = "Jabba the Hutt",
        )

        assertEquals(book, underTest.addBook(book))
        assertEquals(book, underTest.getByIsbn("0000000000001"))
    }

}
