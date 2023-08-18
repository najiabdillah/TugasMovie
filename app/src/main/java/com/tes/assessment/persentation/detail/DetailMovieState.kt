package com.tes.assessment.persentation.detail

import com.tes.assessment.domain.model.detail.DetailMovies

data class DetailMovieState(
    val isLoading: Boolean = false,
    val detailList: List<DetailMovies> = emptyList(),
    val error: String = ""
)