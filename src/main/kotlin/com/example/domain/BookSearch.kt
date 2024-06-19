package com.example.domain

import kotlinx.serialization.Serializable

@Serializable
data class BookSearch(
    val isbn: String? = null,
    val title: String? = null,
    val author: String? = null,
)
