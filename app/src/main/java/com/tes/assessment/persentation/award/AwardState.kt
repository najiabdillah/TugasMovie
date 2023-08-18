package com.tes.assessment.persentation.award

import com.tes.assessment.domain.model.movie.Movies

data class AwardState(
    val isLoading: Boolean = false,
    val searchMovieList: List<Movies> = emptyList(),
    val error: String = ""
)