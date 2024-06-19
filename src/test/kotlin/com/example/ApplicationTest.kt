package com.example

import com.example.domain.Book
import com.example.domain.BookSearch
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.readValue
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {

    private val mapper = ObjectMapper().registerModule(KotlinModule())

    private val expectedBook = Book(
        "1234567890123",
        "Over the Cliff",
        "Eileen Dover",
    )

    @Test
    fun testRoot() = testApplication {
        client.get("/").apply {
            assertEquals(HttpStatusCode.NotImplemented, status)
            assertEquals("", bodyAsText())
        }
    }

    @Test
    fun getByIsbn_returnsExpectedBook() = testApplication {
        client.get("/books/1234567890123").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals(expectedBook, mapper.readValue(bodyAsText()))
        }
    }

    @Test
    fun searchBook_returnsExpectedBook_forIsbn() = testApplication {
        val client = createClient {
            install(io.ktor.client.plugins.contentnegotiation.ContentNegotiation) {
                json()
            }
        }

        val bookSearch = BookSearch(
            isbn = "1234567890123",
        )

        client.post("/books") {
            contentType(ContentType.Application.Json)
            setBody(bookSearch)
        }.apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals(listOf(expectedBook), mapper.readValue(bodyAsText()))
        }
    }

    @Test
    fun searchBook_returnsExpectedBook_forAuthor() = testApplication {
        val client = createClient {
            install(io.ktor.client.plugins.contentnegotiation.ContentNegotiation) {
                json()
            }
        }

        val bookSearch = BookSearch(
            author = "Eileen Dover",
        )

        client.post("/books") {
            contentType(ContentType.Application.Json)
            setBody(bookSearch)
        }.apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals(listOf(expectedBook), mapper.readValue(bodyAsText()))
        }
    }

    @Test
    fun searchBook_returnsExpectedBook_forTitle() = testApplication {
        val client = createClient {
            install(io.ktor.client.plugins.contentnegotiation.ContentNegotiation) {
                json()
            }
        }

        val bookSearch = BookSearch(
            title = "Over the Cliff",
        )

        client.post("/books") {
            contentType(ContentType.Application.Json)
            setBody(bookSearch)
        }.apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals(listOf(expectedBook), mapper.readValue(bodyAsText()))
        }
    }
}
