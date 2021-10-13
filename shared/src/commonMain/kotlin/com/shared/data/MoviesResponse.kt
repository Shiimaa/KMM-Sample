package com.shared.data

import kotlinx.serialization.Serializable

@Serializable
data class MoviesResponse(
    val page: Int? = null,
    val total_pages: Int? = null,
    val total_results: Int? = null,
    val results: List<MovieItem>? = null
)
