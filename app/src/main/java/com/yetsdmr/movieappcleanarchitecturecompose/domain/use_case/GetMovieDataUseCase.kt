package com.yetsdmr.movieappcleanarchitecturecompose.domain.use_case

import com.yetsdmr.movieappcleanarchitecturecompose.data.dto.MovieDataDto
import com.yetsdmr.movieappcleanarchitecturecompose.domain.repository.RemoteDataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMovieDataUseCase @Inject constructor(
    private val repository: RemoteDataRepository
) {
    suspend operator fun invoke(title: String): Flow<MovieDataDto> = flow {
        emit(repository.getMovie(title))
    }
}