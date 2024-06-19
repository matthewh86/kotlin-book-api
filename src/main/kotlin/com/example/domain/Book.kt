package com.example.domain

import kotlinx.serialization.Serializable

// TODO this should be split into a Request/Response object rather than hacking the id to be optional
@Serializable
data class Book(
    var id: Pair<String, Int>? = null,
    val isbn: String,
    val title: String,
    val author: String,
    val reference: Boolean,
    var checkedOut: Boolean = false,
)
