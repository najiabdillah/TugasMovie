package com.tes.assessment.domain.usecase.credit

import com.tes.assessment.data.mappingCreditToUseCaseEntity
import com.tes.assessment.domain.model.credit.Credits
import com.tes.assessment.domain.repository.MovieRepository
import com.tes.assessment.utils.ResultState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CreditUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(
        movieId: String,
        apiKey: String,
        language: String,
    ): Flow<ResultState<List<Credits>>> = flow {
        try {
            emit(ResultState.Loading())
            val movie = repository.getCreditMovie(
                movieId,
                apiKey,
                language
            ).cast.mappingCreditToUseCaseEntity()
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