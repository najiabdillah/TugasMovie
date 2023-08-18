package com.tes.assessment.persentation.detail.review

import com.tes.assessment.domain.model.review.Reviews

data class ReviewMovieState(
    val isLoading: Boolean = false,
    val reviewList: List<Reviews> = emptyList(),
    val error: String = ""
)