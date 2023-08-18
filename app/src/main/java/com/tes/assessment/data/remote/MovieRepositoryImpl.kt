package com.tes.assessment.data.remote

import com.tes.assessment.data.remote.response.credit.CreditMovieResponse
import com.tes.assessment.data.remote.response.detail.DetailMovieResponse
import com.tes.assessment.data.remote.response.genre.GenreResponse
import com.tes.assessment.data.remote.response.movie.MovieResponse
import com.tes.assessment.data.remote.response.review.ReviewResponse
import com.tes.assessment.data.remote.response.trailer.TrailerResponse
import com.tes.assessment.data.remote.services.TmdbApi
import com.tes.assessment.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val tmdbApi: TmdbApi) : MovieRepository {
    override suspend fun getGenre(apiKey: String, language: String): GenreResponse {
        return tmdbApi.getListGenre(apiKey = apiKey, language = language)
    }

    override suspend fun getMovieByGenre(
        apiKey: String,
        language: String,
        withGenre: String,
        page: String
    ): MovieResponse {
        return tmdbApi.getMoviebyGenre(
            apiKey = apiKey,
            language = language,
            withGenres = withGenre,
            page = page
        )
    }

    override suspend fun getReviewByMovie(
        movieId: String,
        apiKey: String,
        language: String
    ): ReviewResponse {
        return tmdbApi.getReviewbyMovie(movieId = movieId, apiKey = apiKey, language = language)
    }

    override suspend fun getTrailerByMovie(
        movieId: String,
        apiKey: String,
        language: String
    ): TrailerResponse {
        return tmdbApi.getTrailerbyMovie(movieId = movieId, apiKey = apiKey, language = language)
    }

    override suspend fun getPopularMovie(apiKey: String, language: String): MovieResponse {
        return tmdbApi.getPopularMovie(apiKey = apiKey, language = language)
    }

    override suspend fun getUpcomingMovie(apiKey: String, language: String): MovieResponse {
        return tmdbApi.getUpcomingMovie(apiKey = apiKey, language = language)
    }

    override suspend fun getSearchMovie(
        apiKey: String,
        language: String,
        query: String
    ): MovieResponse {
        return tmdbApi.getSearchMovie(apiKey = apiKey, language = language, query = query)
    }

    override suspend fun getDetailMovie(
        movieId: String,
        apiKey: String,
        language: String
    ): DetailMovieResponse {
        return tmdbApi.getDetailMovie(movieId = movieId, apiKey = apiKey, language = language)
    }

    override suspend fun getCreditMovie(
        movieId: String,
        apiKey: String,
        language: String
    ): CreditMovieResponse {
        return tmdbApi.getCreditbyMovie(movieId = movieId, apiKey = apiKey, language = language)
    }

}