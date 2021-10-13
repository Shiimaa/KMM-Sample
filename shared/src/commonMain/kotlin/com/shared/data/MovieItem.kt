package com.shared.data

import kotlinx.serialization.Serializable

@Serializable
data class MovieItem(
    val title: String,
    val original_title: String,
    val original_language: String,
    val overview: String,
    val release_date: String,
    val adult: Boolean,
    val video: Boolean,
    val poster_path: String,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val vote_average: Double,
    val popularity: Double,
    val vote_count: Int,
    val id: Int
)
