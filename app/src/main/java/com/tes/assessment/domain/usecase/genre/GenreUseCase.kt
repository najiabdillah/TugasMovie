package com.tes.assessment.domain.usecase.genre

import com.tes.assessment.data.mappingGenreToUseCaseEntity
import com.tes.assessment.domain.model.genre.Genres
import com.tes.assessment.domain.repository.MovieRepository
import com.tes.assessment.utils.ResultState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GenreUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(apiKey: String, language: String): Flow<ResultState<List<Genres>>> = flow {
        try {
            emit(ResultState.Loading())
            val genre = repository.getGenre(apiKey, language).genres.mappingGenreToUseCaseEntity()
            emit(ResultState.Success(genre))
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