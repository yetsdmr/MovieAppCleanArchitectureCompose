package com.yetsdmr.movieappcleanarchitecturecompose.data.repository

import com.yetsdmr.movieappcleanarchitecturecompose.data.MovieApi
import com.yetsdmr.movieappcleanarchitecturecompose.data.dto.MovieDataDto
import com.yetsdmr.movieappcleanarchitecturecompose.data.dto.SearchResultDto
import com.yetsdmr.movieappcleanarchitecturecompose.domain.repository.RemoteDataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoteDataRepositoryImpl @Inject constructor(
    private val api: MovieApi
): RemoteDataRepository {
    override suspend fun getMovie(title: String): MovieDataDto {
        return withContext(Dispatchers.Default) {
            api.getMovieData(title = title)
        }
    }

    override suspend fun getSearchResult(search: String): SearchResultDto {
        return withContext(Dispatchers.Default) {
            api.getSearchResult(search = search)
        }
    }

}