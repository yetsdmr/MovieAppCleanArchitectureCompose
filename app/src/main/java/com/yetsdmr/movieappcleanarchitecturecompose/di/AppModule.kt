package com.yetsdmr.movieappcleanarchitecturecompose.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.yetsdmr.movieappcleanarchitecturecompose.data.MovieApi
import com.yetsdmr.movieappcleanarchitecturecompose.data.repository.RemoteDataRepositoryImpl
import com.yetsdmr.movieappcleanarchitecturecompose.domain.repository.RemoteDataRepository
import com.yetsdmr.movieappcleanarchitecturecompose.domain.use_case.GetMovieDataUseCase
import com.yetsdmr.movieappcleanarchitecturecompose.domain.use_case.GetSearchResultUseCase
import com.yetsdmr.movieappcleanarchitecturecompose.domain.use_case.UseCases
import com.yetsdmr.movieappcleanarchitecturecompose.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @Singleton
    fun provideMovieApi(moshi: Moshi): MovieApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(MovieApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(api: MovieApi): RemoteDataRepository {
        return RemoteDataRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideUseCases(repository: RemoteDataRepository): UseCases {
        return UseCases(
            getMovieDataUseCase = GetMovieDataUseCase(repository),
            getSearchResultUseCase = GetSearchResultUseCase(repository)
        )
    }
}