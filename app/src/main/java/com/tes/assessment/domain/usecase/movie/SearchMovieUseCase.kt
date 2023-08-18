package com.tes.assessment.domain.usecase.movie

import com.tes.assessment.data.mappingSearchMovieToUseCaseEntity
import com.tes.assessment.domain.model.movie.Movies
import com.tes.assessment.domain.repository.MovieRepository
import com.tes.assessment.utils.ResultState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SearchMovieUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(
        apiKey: String,
        language: String,
        query: String
    ): Flow<ResultState<List<Movies>>> =
        flow {
            try {
                emit(ResultState.Loading())
                val movie = repository.getSearchMovie(
                    apiKey,
                    language,
                    query
                ).results.mappingSearchMovieToUseCaseEntity()
                emit(ResultState.Success(movie))
            } catch (e: HttpException) {
                emit(
                    ResultState.Error(
                        e.localizedMessage ?: " An Unexpected Error Occurred"
                    )
                )
            } catch (e: IOException) {
                emit(ResultState.Error("Error Occurred"))
            }
        }
}