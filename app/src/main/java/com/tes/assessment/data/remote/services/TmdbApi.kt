package com.tes.assessment.data.remote.services

import com.tes.assessment.data.remote.response.credit.CreditMovieResponse
import com.tes.assessment.data.remote.response.detail.DetailMovieResponse
import com.tes.assessment.data.remote.response.genre.GenreResponse
import com.tes.assessment.data.remote.response.movie.MovieResponse
import com.tes.assessment.data.remote.response.review.ReviewResponse
import com.tes.assessment.data.remote.response.trailer.TrailerResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbApi {

    // Feature Genre
    @GET("genre/movie/list")
    suspend fun getListGenre(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): GenreResponse

    // Feature Movie by Genre
    @GET("discover/movie")
    suspend fun getMoviebyGenre(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("with_genres") withGenres: String,
        @Query("page") page: String
    ): MovieResponse

    // Feature Review by Movie
    @GET("movie/{movie_id}/reviews")
    suspend fun getReviewbyMovie(
        @Path("movie_id") movieId: String,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): ReviewResponse

    // Feature Trailer by Movie
    @GET("movie/{movie_id}/videos")
    suspend fun getTrailerbyMovie(
        @Path("movie_id") movieId: String,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): TrailerResponse

    // Feature Popular Movie
    @GET("movie/popular")
    suspend fun getPopularMovie(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): MovieResponse

    @GET("movie/upcoming")
    suspend fun getUpcomingMovie(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): MovieResponse

    // Feature Search Movie
    @GET("search/movie")
    suspend fun getSearchMovie(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("query") query: String
    ): MovieResponse

    // Feature Detail Movie
    @GET("movie/{movie_id}")
    suspend fun getDetailMovie(
        @Path("movie_id") movieId: String,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): DetailMovieResponse

    // Feature Trailer by Movie
    @GET("movie/{movie_id}/credits")
    suspend fun getCreditbyMovie(
        @Path("movie_id") movieId: String,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): CreditMovieResponse
}