package com.yetsdmr.movieappcleanarchitecturecompose.domain.repository

import com.yetsdmr.movieappcleanarchitecturecompose.data.dto.MovieDataDto
import com.yetsdmr.movieappcleanarchitecturecompose.data.dto.SearchResultDto

interface RemoteDataRepository {

    suspend fun getMovie(title: String): MovieDataDto

    suspend fun getSearchResult(search: String): SearchResultDto

}