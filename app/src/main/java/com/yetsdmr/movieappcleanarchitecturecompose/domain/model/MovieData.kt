package com.yetsdmr.movieappcleanarchitecturecompose.domain.model

import com.squareup.moshi.Json
import com.yetsdmr.movieappcleanarchitecturecompose.data.dto.Rating

data class MovieData(
    val title: String,
    val year: String,
    val rated: String,
    val released: String,
    val runtime: String,
    val genre: String,
    val director: String,
    val writer: String,
    val actors: String,
    val plot: String,
    val language: String,
    val country: String,
    val awards: String,
    val poster: String,
    val ratings: List<Rating>,
    val imdbRating: String,
    val boxOffice: String,
    val production: String
)
