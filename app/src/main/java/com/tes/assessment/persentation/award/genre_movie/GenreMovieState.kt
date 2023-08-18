package com.tes.assessment.persentation.award.genre_movie

import com.tes.assessment.domain.model.movie.Movies

data class GenreMovieState(
    val isLoading: Boolean = false,
    val genreMovieList: List<Movies> = emptyList(),
    val error: String = ""
)