package com.tes.assessment.persentation.dashboard.popular

import com.tes.assessment.domain.model.movie.Movies

data class PopularState(
    val isLoading: Boolean = false,
    val popularList: List<Movies> = emptyList(),
    val error: String = ""
)