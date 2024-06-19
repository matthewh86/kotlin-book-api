package com.example.plugins

import com.example.domain.BookSearch
import com.example.service.BookServiceImpl
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

// TODO use koin DI
private val bookService = BookServiceImpl()

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respond(HttpStatusCode.NotImplemented)
        }
        get("/books/{isbn}") {
            val isbn = call.parameters["isbn"]
            if (isbn != null) {
                val book = bookService.getByIsbn(isbn)!!
                call.respond(book)
            }
            call.respond(HttpStatusCode.BadRequest)
        }
        post("/books") {
            val bookSearch = call.receive<BookSearch>()
            if (bookSearch != null) {
                val book = bookService.bookSearch(bookSearch)!!
                call.respond(book)
            }
            call.respond(HttpStatusCode.BadRequest)
        }
    }
}
