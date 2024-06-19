package com.example.repository

import com.example.domain.Book
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
    fun getBookByIsbn_returnsRequestedBook() {
        assertEquals(expected, underTest.getBookByIsbn("1234567890123"))
    }
    @Test
    fun getBookByIsbn_returnsNoBook() {
        assertNull(underTest.getBookByIsbn("nobook"))
    }

}
