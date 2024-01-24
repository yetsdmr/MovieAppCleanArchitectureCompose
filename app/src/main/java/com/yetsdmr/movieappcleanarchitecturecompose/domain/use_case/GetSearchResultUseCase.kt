package com.yetsdmr.movieappcleanarchitecturecompose.domain.use_case

import com.yetsdmr.movieappcleanarchitecturecompose.data.dto.SearchResultDto
import com.yetsdmr.movieappcleanarchitecturecompose.domain.repository.RemoteDataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSearchResultUseCase @Inject constructor(
    private val repository: RemoteDataRepository
){
    suspend operator fun invoke(search: String): Flow<SearchResultDto> = flow {
        emit(repository.getSearchResult(search))
    }
}