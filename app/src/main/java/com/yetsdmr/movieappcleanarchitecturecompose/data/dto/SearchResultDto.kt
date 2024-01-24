package com.yetsdmr.movieappcleanarchitecturecompose.data.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.yetsdmr.movieappcleanarchitecturecompose.domain.model.SearchResult

@JsonClass(generateAdapter = true)
data class SearchResultDto(
    @Json(name = "Search")
    val movies: List<Movie>,
    @Json(name = "totalResults")
    val totalResults: String,
    @Json(name = "Response")
    val response: String
)

fun SearchResultDto.toSearchResult(): SearchResult {
    return SearchResult(movies = movies)
}
