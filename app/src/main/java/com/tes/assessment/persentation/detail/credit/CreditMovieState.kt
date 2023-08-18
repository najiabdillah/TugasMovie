package com.tes.assessment.persentation.detail.credit

import com.tes.assessment.domain.model.credit.Credits

data class CreditMovieState(
    val isLoading: Boolean = false,
    val creditList: List<Credits> = emptyList(),
    val error: String = ""
)
