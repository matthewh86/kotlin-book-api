package com.example

import com.example.domain.Book
import com.example.plugins.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.*
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.readValue

class ApplicationTest {

    private val mapper = ObjectMapper().registerModule(KotlinModule())

    @Test
    fun testRoot() = testApplication {
        client.get("/").apply {
            assertEquals(HttpStatusCode.NotImplemented, status)
            assertEquals("", bodyAsText())
        }
    }

    @Test
    fun testBooks_getByIsbn() = testApplication {
        val expectedBook = Book(
            "1234567890123",
            "Over the Cliff",
            "Eileen Dover",
        )

        client.get("/books/1234567890123").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals(expectedBook, mapper.readValue(bodyAsText()))
        }
    }
}
