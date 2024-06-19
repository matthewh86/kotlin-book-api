package com.example.repository

import com.example.domain.Book
import kotlin.test.Test
import kotlin.test.assertEquals

class BookRepositoryImplTest {

    private val underTest = BookRepositoryImpl()
    private val expected = Book(
        "1234567890123",
        "Over the Cliff",
        "Eileen Dover",
    )

    @Test
    fun returnsRequestedBook() {
        assertEquals(expected, underTest.getBookByIsbn("1234567890123"))
    }

}
