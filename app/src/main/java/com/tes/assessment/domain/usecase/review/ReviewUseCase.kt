package com.tes.assessment.domain.usecase.review

import com.tes.assessment.data.mappingReviewToUseCaseEntity
import com.tes.assessment.domain.model.review.Reviews
import com.tes.assessment.domain.repository.MovieRepository
import com.tes.assessment.utils.ResultState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ReviewUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(
        movieId: String,
        apiKey: String,
        language: String
    ): Flow<ResultState<List<Reviews>>> = flow {
        try {
            emit(ResultState.Loading())
            val review = repository.getReviewByMovie(
                movieId,
                apiKey,
                language
            ).results.mappingReviewToUseCaseEntity()
            emit(ResultState.Success(review))
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