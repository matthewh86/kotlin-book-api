package com.example.domain

import kotlinx.serialization.Serializable

@Serializable
data class BookSearch(
    val id: String? = null,
    val isbn: String? = null,
    val title: String? = null,
    val author: String? = null,
    val reference: Boolean? = null,
    val checkedOut: Boolean? = null,
)
