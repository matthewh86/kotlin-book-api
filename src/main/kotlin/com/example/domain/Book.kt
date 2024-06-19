package com.example.domain

import kotlinx.serialization.Serializable

@Serializable
data class Book(
    val id: String,
    val isbn: String,
    val title: String,
    val author: String,
    val reference: Boolean,
    var checkedOut: Boolean,
)
