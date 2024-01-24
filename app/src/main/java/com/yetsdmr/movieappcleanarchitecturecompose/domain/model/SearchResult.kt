package com.yetsdmr.movieappcleanarchitecturecompose.domain.model

import com.yetsdmr.movieappcleanarchitecturecompose.data.dto.Movie

data class SearchResult(
    val movies: List<Movie>
)
