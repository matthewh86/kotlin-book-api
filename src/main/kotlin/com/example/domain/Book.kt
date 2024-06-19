package com.example.domain

import kotlinx.serialization.Serializable

@Serializable
data class Book(
    val isbn: String,
    val title: String,
    val author: String
)
