package com.example.repository

import com.example.domain.Book
import com.example.domain.BookSearch
import kotlin.test.Test
import kotlin.test.assertEquals

class BookRepositoryImplTest {

    private val underTest = BookRepositoryImpl()
    private val expected = Book(
        id = "1234567890123-1",
        isbn = "1234567890123",
        title = "Over the Cliff",
        author = "Eileen Dover",
        reference = false,
        checkedOut = false,
    )

    @Test
    fun getByIsbn_returnsRequested() {
        assertEquals(expected, underTest.getByIsbn("1234567890123").firstOrNull())
    }

    @Test
    fun getByIsbn_returnsEmpty_whenNotExists() {
        assertEquals(emptyList(), underTest.getByIsbn("nobook"))
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
            id = "0000000000001-1",
            isbn = "0000000000001",
            title = "Hutt Economics",
            author = "Jabba the Hutt",
            reference = false,
            checkedOut = false,
        )

        assertEquals(book, underTest.addBook(book))
        assertEquals(book, underTest.getByIsbn("0000000000001").firstOrNull())
    }

}
